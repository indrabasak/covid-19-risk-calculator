package org.dristhi.covid19.data.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.dristhi.covid19.data.entity.CovidCountyPopulation;
import org.dristhi.covid19.data.repository.CovidCountyPopulationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CovidCountyPopulationInitializer implements CommandLineRunner {

    private final ResourceLoader loader;

    private final String location;

    private final CovidCountyPopulationRepository repository;

    public CovidCountyPopulationInitializer(ResourceLoader loader,
                                            @Value("${csv.covidcountypopulation}") String location,
                                            CovidCountyPopulationRepository repository) {
        this.loader = loader;
        this.location = location;
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        InputStream istream = getResource(loader, location);
        List<CovidCountyPopulation> list = convertCsv(istream);
        list.stream().forEach(p -> repository.save(p));
    }

    private List<CovidCountyPopulation> convertCsv(InputStream istream) {
        try (BufferedReader fileReader =
                     new BufferedReader(new InputStreamReader(istream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<CovidCountyPopulation> populationList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                CovidCountyPopulation population = new CovidCountyPopulation();
                population.setFips(Integer.parseInt(csvRecord.get("\uFEFFcountyFIPS")));
                population.setName(csvRecord.get("County Name"));
                population.setState(csvRecord.get("State"));
                population.setPopulation(Integer.parseInt(csvRecord.get("population")));
                populationList.add(population);
            }

            return populationList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


    private InputStream getResource(ResourceLoader loader, String location) {
        InputStream istream = null;
        try {
            istream = loader.getResource(location).getInputStream();
            log.info(location + " found!");
        } catch (IOException e) {
            log.debug(location + " not found!", e);
        }

        return istream;
    }
}

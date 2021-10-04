package org.dristhi.covid19.data.reader;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.dristhi.covid19.config.DataProperties;
import org.dristhi.covid19.data.entity.CovidCountyPopulation;
import org.dristhi.covid19.data.repository.CovidCountyPopulationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CovidCountyPopulationInitializer
        implements AbstractCsvToDatabaseInitializer<CovidCountyPopulation>,
        CommandLineRunner {

    private final ResourceLoader loader;

    private final DataProperties properties;

    private final CovidCountyPopulationRepository repository;

    public CovidCountyPopulationInitializer(ResourceLoader loader,
                                            DataProperties properties,
                                            CovidCountyPopulationRepository repository) {
        this.loader = loader;
        this.properties = properties;
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        List<CovidCountyPopulation> list = convertCsv(getResource(loader, properties.getCsv().get("covidCountyPopulation")));
        list.forEach(repository::save);
    }

    @Override
    public CovidCountyPopulation parse(CSVRecord csvRecord) {
        CovidCountyPopulation row = new CovidCountyPopulation();
        row.setFips(Integer.parseInt(csvRecord.get("\uFEFFcountyFIPS")));
        row.setCounty(csvRecord.get("County Name"));
        row.setState(csvRecord.get("State"));
        row.setPopulation(Integer.parseInt(csvRecord.get("population")));

        return row;
    }
}

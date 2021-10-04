package org.dristhi.covid19.data.reader;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.dristhi.covid19.config.DataProperties;
import org.dristhi.covid19.data.entity.CovidConfirmed;
import org.dristhi.covid19.data.repository.CovidConfirmedRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CovidConfirmedInitializer implements
        AbstractCsvToDatabaseInitializer<CovidConfirmed>, CommandLineRunner {

    private final ResourceLoader loader;

    private final DataProperties properties;

    private final CovidConfirmedRepository repository;

    public CovidConfirmedInitializer(ResourceLoader loader,
                                     DataProperties properties,
                                     CovidConfirmedRepository repository) {
        this.loader = loader;
        this.properties = properties;
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        String location = properties.getCsv().get("covidConfirmed");
        List<CovidConfirmed> list = convertCsv(getResource(loader, location));
        list.forEach(repository::save);
    }

    @Override
    public CovidConfirmed parse(CSVRecord csvRecord) {
        CovidConfirmed row = new CovidConfirmed();
        row.setFips(Integer.parseInt(csvRecord.get("countyFIPS")));
        row.setCounty(csvRecord.get("County Name"));
        row.setState(csvRecord.get("State"));

        LocalDate startDate = LocalDate.of(2020, 1, 22);
        LocalDate endDate = LocalDate.of(2021, 10, 1);
        List<LocalDate> dates = getDatesBetween(startDate, endDate);
        dates.forEach(d -> {
            String methodName = "setD" + d.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String property = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            setCovidDeaths(row, methodName, property, csvRecord);
        });

        return row;
    }

    private List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    private void setCovidDeaths(CovidConfirmed entity, String methodName,
                                String property, CSVRecord csvRecord) {
        try {
            Method method
                    = CovidConfirmed.class.getMethod(methodName, int.class);
            method.invoke(entity, Integer.parseInt(csvRecord.get(property)));
        } catch (Exception e) {
            log.error("Failed to invoke method: {} with property {}.", methodName, property);
        }
    }
}

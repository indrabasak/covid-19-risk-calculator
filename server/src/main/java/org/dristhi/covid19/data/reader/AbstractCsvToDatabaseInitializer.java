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
import org.springframework.core.io.ResourceLoader;

@Slf4j
public abstract class AbstractCsvToDatabaseInitializer<T> {

    public List<T> convertCsv(InputStream istream) {
        try (BufferedReader fileReader =
                     new BufferedReader(new InputStreamReader(istream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<T> list = new ArrayList<>();

            Iterable<CSVRecord> records = csvParser.getRecords();

            for (CSVRecord csvRecord : records) {
                list.add(parse(csvRecord));
            }

            return list;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public abstract T parse(CSVRecord csvRecord);

    public InputStream getResource(ResourceLoader loader, String location) {
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

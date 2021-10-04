package org.dristhi.covid19.data.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.dristhi.covid19.error.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;

public interface AbstractCsvToDatabaseInitializer<T> {
    Logger log = LoggerFactory.getLogger(AbstractCsvToDatabaseInitializer.class);

    default List<T> convertCsv(InputStream istream) {
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(istream, StandardCharsets.UTF_8));
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader().setSkipHeaderRecord(true)
                    .setIgnoreHeaderCase(true)
                    .setTrim(true).build();
            CSVParser csvParser = new CSVParser(reader, csvFormat);

            List<T> list = new ArrayList<>();
            Iterable<CSVRecord> records = csvParser.getRecords();
            for (CSVRecord csvRecord : records) {
                list.add(parse(csvRecord));
            }

            return list;
        } catch (IOException e) {
            throw new ResourceNotFoundException("fail to parse CSV file: " + e.getMessage());
        }
    }

    T parse(CSVRecord csvRecord);

    default InputStream getResource(ResourceLoader loader, String location) {
        InputStream istream = null;
        try {
            istream = loader.getResource(location).getInputStream();
            log.info("{} found!", location);
        } catch (IOException e) {
            log.debug(" {} not found!", location, e);
        }

        return istream;
    }
}

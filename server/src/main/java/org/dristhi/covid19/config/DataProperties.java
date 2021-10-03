package org.dristhi.covid19.config;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("data")
@Getter
@Setter
public class DataProperties {

    private Map<String, String> csv = new HashMap<>();

    private Map<String, Vaccine> vaccines = new HashMap<>();

    @Getter
    @Setter
    public static class Vaccine {
        private Map<Integer, Double> multipliers = new HashMap<>();
    }

    @Getter
    @Setter
    public static class Csv {
        private String path;
    }

}

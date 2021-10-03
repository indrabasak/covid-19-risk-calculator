package org.dristhi.covid19.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DataProperties.class)
@Slf4j
@Getter
public class DataConfiguration {

    private DataProperties properties;

    public DataConfiguration(DataProperties properties) {
        this.properties = properties;
    }
}

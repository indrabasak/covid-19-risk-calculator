package org.dristhi.covid19.service;

import java.util.Locale;
import org.dristhi.covid19.config.DataProperties;
import org.dristhi.covid19.model.RiskRequest;
import org.springframework.stereotype.Service;

@Service
public class CovidRiskService {

    private DataProperties properties;

    public CovidRiskService(DataProperties properties) {
        this.properties = properties;
    }

    public double calculate(RiskRequest request) {
        return calculateVaccineRisk(request.getVaccine().getProvider(),
                request.getVaccine().getDoses());
    }

    private double calculateVaccineRisk(String provider, int doses) {
        DataProperties.Vaccine vaccine;

        if (properties.getVaccines().containsKey(provider.toLowerCase(Locale.ROOT))) {
            vaccine = properties.getVaccines().get(provider.toLowerCase(Locale.ROOT));
        } else {
            vaccine = properties.getVaccines().get("unknown");
        }

        return vaccine.getMultipliers().get(doses);
    }
}

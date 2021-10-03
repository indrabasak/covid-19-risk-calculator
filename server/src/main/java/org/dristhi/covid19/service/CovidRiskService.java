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
        double multiplier = calculateVaccineRisk(request.getVaccine(), request.getDoses());

        return multiplier;
    }

    private double calculateVaccineRisk(String vaccineName, int doses) {
        DataProperties.Vaccine vaccine;

        if (properties.getVaccines().containsKey(vaccineName.toLowerCase(Locale.ROOT))) {
            vaccine = properties.getVaccines().get(vaccineName.toLowerCase(Locale.ROOT));
        } else {
            vaccine = properties.getVaccines().get("unknown");
        }

        return vaccine.getMultipliers().get(doses);
    }
}
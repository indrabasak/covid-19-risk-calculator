package org.dristhi.covid19.controller;

import org.dristhi.covid19.model.RiskRequest;
import org.dristhi.covid19.service.CovidRiskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidRiskController {

    private CovidRiskService service;

    public CovidRiskController(CovidRiskService service) {
        this.service = service;
    }

    //@ApiOperation(value = "Creates a book.", response = Double.class)
    @PostMapping(value = "/calculate")
    @ResponseStatus(HttpStatus.CREATED)
    public double calculate(@RequestBody RiskRequest request) {
        return service.calculate(request);
    }
}

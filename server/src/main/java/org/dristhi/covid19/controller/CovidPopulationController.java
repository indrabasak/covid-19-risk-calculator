package org.dristhi.covid19.controller;

import java.util.List;
import org.dristhi.covid19.data.entity.CovidCountyPopulation;
import org.dristhi.covid19.data.repository.CovidCountyPopulationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidPopulationController {

    private final CovidCountyPopulationRepository repository;

    public CovidPopulationController(CovidCountyPopulationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/population/{state}/{county}")
    public int getPopulationByStateAndCounty(@PathVariable String state, @PathVariable String county) {
       List<CovidCountyPopulation> list = repository.findByStateAndCounty(state, county + " County");
       if (!list.isEmpty()) {
           return list.get(0).getPopulation();
       }

        return 0;
    }
}

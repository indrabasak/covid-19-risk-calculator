package org.dristhi.covid19.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.Data;

@Entity
@IdClass(CovidCountyPopulationId.class)
@Data
public class CovidCountyPopulation {

    @Id
    private int fips;

    private String name;

    @Id
    private String state;

    private int population;
}

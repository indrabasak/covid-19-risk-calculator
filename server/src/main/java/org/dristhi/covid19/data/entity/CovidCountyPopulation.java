package org.dristhi.covid19.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CovidCountyPopulation {

    @Id
    private int fips;

    private String name;

    private String state;

    private int population;
}

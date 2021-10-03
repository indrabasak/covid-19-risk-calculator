package org.dristhi.covid19.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.Data;

@Entity
@IdClass(CountyFipsStateId.class)
@Data
public class CovidCountyPopulation {

    @Id
    private int fips;

    @Id
    private String state;

    private String county;

    private int population;
}

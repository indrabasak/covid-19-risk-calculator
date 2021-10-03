package org.dristhi.covid19.data.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidCountyPopulationId implements Serializable {

    private int fips;

    private String state;
}

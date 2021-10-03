package org.dristhi.covid19.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RiskRequest {

    private String vaccine;

    private int doses;

    @JsonCreator
    public RiskRequest(@JsonProperty("vaccine") String vaccine,
                       @JsonProperty("doses") int doses) {
        this.vaccine = vaccine;
        this.doses = doses;
    }
}

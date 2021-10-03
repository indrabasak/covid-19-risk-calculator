package org.dristhi.covid19.data.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountyFipsStateId implements Serializable {

    private int fips;

    private String state;
}

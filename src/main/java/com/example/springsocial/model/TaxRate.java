package com.example.springsocial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxRate {

    public final float totalRate;

    public TaxRate(@JsonProperty("totalRate") float totalRate) {
        this.totalRate = totalRate;
    }
;

}

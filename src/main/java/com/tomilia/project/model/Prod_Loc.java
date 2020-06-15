package com.tomilia.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table
public class Prod_Loc {

    @ManyToOne
    private final String p_code;
    @NotBlank
    private final int amount;
    private final String location;

    public Prod_Loc(@JsonProperty("p_code") String p_code, @JsonProperty("amount") int amount, @JsonProperty("location") String location) {
        this.p_code = p_code;
        this.amount = amount;
        this.location = location;
    }
    public String getP_code() {
        return p_code;
    }
    public int getAmount() {
        return amount;
    }

    public String getLocation() {
        return location;
    }

}

package com.tomilia.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

import javax.validation.constraints.NotBlank;

public class Prod_Loc {
    @CsvBindByName(column = "Code")
    private String p_code;
    @NotBlank
    @CsvBindByName(column = "Amount")
    private int amount;
    @CsvBindByName(column = "Location")
    private String location;
    public Prod_Loc(){}
    public Prod_Loc(String p_code,int amount,String location) {
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
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}

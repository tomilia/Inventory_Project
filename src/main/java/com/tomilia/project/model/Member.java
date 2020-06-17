package com.tomilia.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

import javax.validation.constraints.NotBlank;

public class Member {

    @CsvBindByName(column = "Code")
    private String p_code;
    @NotBlank
    @CsvBindByName(column = "Name")
    private String p_name;
    @CsvBindByName(column = "Weight")
    private int p_weight;
    public Member(){}
    public Member(String p_code, String p_name, int p_weight) {
        this.p_code = p_code;
        this.p_name = p_name;
        this.p_weight = p_weight;
    }

    public int getP_weight() {
        return p_weight;
    }

    public String getP_code() {
        return p_code;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_weight(int p_weight) {
        this.p_weight = p_weight;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

}

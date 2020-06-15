package com.tomilia.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Member {


    private final String p_code;
    @NotBlank
    private final String p_name;



    private final int p_weight;
    public Member(@JsonProperty("p_code") String p_code, @JsonProperty("p_name") String p_name, int p_weight) {
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

}

package com.tomilia.project.model;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class Compo_Product_Info {

    @NotBlank
    private Member member;



    private List<Prod_Loc> prod_loc_list;

    public Compo_Product_Info() {}

    public Compo_Product_Info(Member member, List<Prod_Loc> prod_loc_list) {
        this.member = member;
        this.prod_loc_list = prod_loc_list;
    }
    public Member getMember() {
        return member;
    }

    public List<Prod_Loc> getProd_loc_list() {
        return prod_loc_list;
    }

}

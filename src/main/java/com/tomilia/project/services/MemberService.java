package com.tomilia.project.services;

import com.tomilia.project.data_access_object.Member_Dao;
import com.tomilia.project.model.Compo_Product_Info;
import com.tomilia.project.model.Member;
import com.tomilia.project.model.Prod_Loc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final Member_Dao member_dao;

    @Autowired
    public MemberService(@Qualifier("mysql") Member_Dao member_dao) {
        this.member_dao = member_dao;
    }

    public List<Member> getAllPeople() {
        return member_dao.selectAllMember();
    }

    public Compo_Product_Info getMemberByID(String id) {
        return member_dao.selectMemberByID(id);
    }
    public String addProductDetailFromCSV(List<Member> members) {
        return member_dao.addProductDetailFromCSV(members);
    }
    public String setProductQuantityFromCSV(List<Prod_Loc> prod_locs) {
        return member_dao.setProductQuantityFromCSV(prod_locs);
    }
    public String transferInventory(String p_code, int amount, String from, String to) {
        return member_dao.transferInventory(p_code, amount, from, to);
    }
}

package com.tomilia.project.services;

import com.tomilia.project.data_access_object.Product_Dao;
import com.tomilia.project.model.Compo_Product_Info;
import com.tomilia.project.model.Member;
import com.tomilia.project.model.Prod_Loc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final Product_Dao product_dao;

    @Autowired
    public ProductService(@Qualifier("mysql") Product_Dao product_dao) {
        this.product_dao = product_dao;
    }

    public List<Member> getAllPeople() {
        return product_dao.selectAllMember();
    }

    public Compo_Product_Info getMemberByID(String id) {
        return product_dao.selectMemberByID(id);
    }
    public String addProductDetailFromCSV(List<Member> members) {
        return product_dao.addProductDetailFromCSV(members);
    }
    public String setProductQuantityFromCSV(List<Prod_Loc> prod_locs) {
        return product_dao.setProductQuantityFromCSV(prod_locs);
    }
    public String transferInventory(String p_code, int amount, String from, String to) {
        return product_dao.transferInventory(p_code, amount, from, to);
    }
}

package com.tomilia.project.data_access_object;

import com.tomilia.project.model.Compo_Product_Info;
import com.tomilia.project.model.Member;
import com.tomilia.project.model.Prod_Loc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Member_Dao {
    int insertMember(String id, Member member);

    List<Member> selectAllMember();

    default int insertMember(Member member) {
        String id = "HKTV-01";
        return insertMember(id, member);
    }


    Compo_Product_Info selectMemberByID(String id);

    int deleteMemberById(String id);

    String setProductQuantityFromCSV(List<Prod_Loc> prod_locs);

    String addProductDetailFromCSV(List<Member> members);

    String transferInventory(String p_code,int amount,String from,String to);

    int updateMemberById(String id, Member member);

}

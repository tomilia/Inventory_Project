package com.tomilia.project.services;

import com.tomilia.project.data_access_object.Member_Dao;
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

    public int addMember(Member member) {
        return member_dao.insertMember(member);
    }

    public List<Member> getAllPeople() {
        return member_dao.selectAllMember();
    }

    public List<Prod_Loc> getMemberByID(String id) {
        return member_dao.selectMemberByID(id);
    }

    public String transferInventory(String p_code, int amount, String from, String to) {
        return member_dao.transferInventory(p_code, amount, from, to);
    }

    public int deleteMemberByID(String id) {
        return member_dao.deleteMemberById(id);
    }

    public int updateMemberByID(String id, Member member) {
        return member_dao.updateMemberById(id, member);
    }
}

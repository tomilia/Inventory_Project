package com.tomilia.project.data_access_object;

import com.tomilia.project.model.Member;
import com.tomilia.project.model.Prod_Loc;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeDao")
public class FakeMemberAccess implements Member_Dao {
    private static List<Member> DB = new ArrayList<>();

    public int insertMember(String p_name, Member member) {
        DB.add(new Member(p_name, member.getP_name(), member.getP_weight()));
        return 1;
    }

    @Override
    public List<Member> selectAllMember() {
        return DB;
    }

    @Override
    public List<Prod_Loc> selectMemberByID(String p_name) {
        return null;

    }

    @Override
    public int deleteMemberById(String p_name) {
//        Optional<Member> member = selectMemberByID(p_name);
//        if (member.isEmpty()) {
//            return 0;
//        }
//        DB.remove(member.get());
//        return 1;
        return 1;
    }

    @Override
    public String transferInventory(String p_code, int amount, String from, String to) {
        return null;
    }

    @Override
    public int updateMemberById(String p_name, Member member) {
        return 1;
//        return selectMemberByID(p_name).map(p -> {
//            int indexOfPerson = DB.indexOf(p);
//            if (indexOfPerson >= 0) {
//                DB.set(indexOfPerson, new Member(p_name, member.getP_name()));
//                return 1;
//            }
//            return 0;
//        }).orElse(0);

    }


}

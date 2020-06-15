package com.tomilia.project.data_access_object;

import com.tomilia.project.model.Member;
import com.tomilia.project.model.Prod_Loc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mysql")
public class MemberDataAccessService implements Member_Dao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertMember(String p_code, Member member) {
        return 0;
    }

    @Override
    public List<Member> selectAllMember() {
        final String sql = "SELECT * FROM Product";

        List<Member> members = jdbcTemplate.query(sql, ((resultSet, i) -> {
            return new Member(resultSet.getString("p_code"), resultSet.getString("p_name"), resultSet.getInt("p_weight"));
        }));

        return members;
    }

    /*
    Show Inventory amount
     */
    @Override
    public List<Prod_Loc> selectMemberByID(String p_code) {
        final String sql = "SELECT * FROM Prod_Loc WHERE p_code = ?";

        List<Prod_Loc> members = jdbcTemplate.query(sql,
                new Object[]{p_code}, ((resultSet, i) -> {
                    return new Prod_Loc(resultSet.getString("p_code"), resultSet.getInt("amount"), resultSet.getString("location"));
                }));

        return members;
    }

    @Override
    public int deleteMemberById(String p_code) {
        return 0;
    }

    @Override
    public String transferInventory(String p_code, int amount, String from, String to) {
        /*
            case 1: code not found
            case 2: from location not found
            case 3: amount not enough
            case 4: success, minus from location plus to location , if location does not have inventory -> create
         */
        //case 1
        if (selectMemberByID(p_code).size() == 0) {
            return "No such product";
        }
        //case 2
        int have_inventory = 0;
        final String sql_one = "SELECT COUNT(*) FROM Prod_Loc WHERE p_code = ? AND location = ?";
        have_inventory = jdbcTemplate.query(sql_one,
                new Object[]{p_code, from}, ((resultSet, i) -> {
                    int idx = resultSet.getInt(1);
                    return idx;
                })).get(0);
        if (have_inventory == 0)
            return "Product in location not found";
        //case 3
        int remain_amount = check_amount_enough(p_code, from, amount);
        if (remain_amount < 0)
            return "Not enough products from source destination";
        //case 4 (update amount, create if not exists)
        update_inventory_from_source(p_code, from, remain_amount);
        create_or_update_inventory_dest(p_code, to, amount);
        return "Transfer succeed!";
    }

    public int create_or_update_inventory_dest(String p_code, String to, int insert_amount) {
        final String sql_four = "INSERT INTO Prod_Loc (p_code, amount, location)" +
                "SELECT * FROM (SELECT ?, ?, ?) AS tmp " +
                "WHERE NOT EXISTS (" +
                "SELECT p_code,location FROM Prod_Loc WHERE p_code = ? AND location = ?" +
                ") LIMIT 1;";
        int flag = jdbcTemplate.update(sql_four,
                p_code, insert_amount, to, p_code, to);
        //if flag == 0 record exists, perform update
        if (flag == 0) {
            final String sql_x = "UPDATE Prod_Loc SET amount = amount + ? WHERE p_code = ? AND location = ?";
            jdbcTemplate.update(sql_x, insert_amount, p_code, to);
        }
        return flag;
    }

    public int update_inventory_from_source(String p_code, String from, int remain_amount) {
        final String sql_three = "UPDATE Prod_Loc SET amount = ? WHERE p_code = ? AND location = ?";
        int flag = jdbcTemplate.update(sql_three,
                remain_amount, p_code, from);
        //DELETE IF AMOUNT = 0?
        final String sql = "DELETE FROM Prod_Loc WHERE amount = 0;";
        jdbcTemplate.update(sql);

        return flag;
    }

    public int check_amount_enough(String p_code, String from, int amount) {
        final String sql_two = "SELECT amount FROM Prod_Loc WHERE p_code = ? AND location = ?";
        int inventory_amount;
        inventory_amount = jdbcTemplate.query(sql_two,
                new Object[]{p_code, from}, ((resultSet, i) -> {
                    int idx = resultSet.getInt("amount");
                    return idx;
                })).get(0);
        return inventory_amount - amount;
    }

    @Override
    public int updateMemberById(String p_code, Member member) {
        return 0;
    }
}

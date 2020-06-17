package com.tomilia.project.data_access_object;

import com.tomilia.project.model.Compo_Product_Info;
import com.tomilia.project.model.Member;
import com.tomilia.project.model.Prod_Loc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mysql")
public class ProductDataAccessService implements Product_Dao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ProductDataAccessService(JdbcTemplate jdbcTemplate) {
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
    public Compo_Product_Info selectMemberByID(String p_code) {
        final String sql_get = "SELECT * FROM Product WHERE p_code = ?";
        final String sql = "SELECT * FROM Prod_Loc,Product WHERE Product.p_code = Prod_Loc.p_code AND Product.p_code = ?";
        Member inv_item_detail;
        try {
            inv_item_detail = jdbcTemplate.query(sql_get, new Object[]{p_code}, ((resultSet, i) -> {
                return new Member(resultSet.getString("p_code"),
                        resultSet.getString("p_name"),
                        resultSet.getInt("p_weight"));
            })).get(0);
        } catch (Exception e) {
            return null;
        }
        List<Prod_Loc> members = jdbcTemplate.query(sql,
                new Object[]{p_code}, ((resultSet, i) -> {
                    return new Prod_Loc(resultSet.getString("p_code"),
                            resultSet.getInt("amount"),
                            resultSet.getString("location"));
                }));

        return new Compo_Product_Info(inv_item_detail, members);
    }

    @Override
    public int deleteMemberById(String p_code) {
        return 0;
    }

    @Override
    public String setProductQuantityFromCSV(List<Prod_Loc> prod_locs) {
        final String sql_delete = "DELETE FROM Prod_Loc";
        final String sql_insert = "INSERT IGNORE INTO Prod_Loc" +
                " (p_code, amount, location) " +
                "VALUES" + " (?, ?, ?);";

        for (Prod_Loc x : prod_locs) {
            if (x.getP_code() == null || x.getLocation() == null || x.getAmount() == 0) {
                System.out.println("Wrong Format of CSV");
                return "Wrong Format of CSV";
            }
        }
        jdbcTemplate.update(sql_delete);
        int flag = 0;
        for (Prod_Loc x : prod_locs) {
            flag += jdbcTemplate.update(sql_insert,
                    x.getP_code(), x.getAmount(), x.getLocation());
            System.out.println(flag);
        }
        if (flag == 0) {
            return "Please import the Product data first";
        }
        return "Inventory Update Successfully!";
    }

    @Override
    public String addProductDetailFromCSV(List<Member> members) {
        //insert if not exists product in product table
        final String sql_delete = "DELETE FROM Product";
        final String sql_insert = "INSERT IGNORE INTO Product" +
                " (p_code, p_name, p_weight) " +
                "VALUES" + " (?, ?, ?);";
        for (Member x : members) {
            if (x.getP_code() == null || x.getP_name() == null || x.getP_weight() == 0) {
                System.out.println("Wrong Format of CSV");
                return "Wrong Format of CSV";
            }
        }
        jdbcTemplate.update(sql_delete);
        int flag = 0;
        for (Member x : members) {
            flag += jdbcTemplate.update(sql_insert,
                    x.getP_code(), x.getP_name(), x.getP_weight());

        }

        return "Inventory Update Successfully!";
    }

    @Override
    public String transferInventory(String p_code, int amount, String from, String to) {
        /*
            case 1: amount <=0, code not found
            case 2: from location not found
            case 3: amount not enough
            case 4: success, minus from location plus to location , if location does not have inventory -> create
         */
        //case 1
        if (amount <= 0) {
            return "Quantity must be larger or equal to 0";
        } else if (selectMemberByID(p_code) == null) {
            //wrong code
            return "No such product";
        } else if (selectMemberByID(p_code).getProd_loc_list().size() == 0) {
            //have product but no inventory level
            return "The product does not exist in inventory";
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

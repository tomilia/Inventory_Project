package com.tomilia.project;

import com.tomilia.project.data_access_object.ProductDataAccessService;
import com.tomilia.project.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
@ContextConfiguration(classes=ProjectApplication.class)
@Import(ProductDataAccessService.class)
@Transactional
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class InsertProductTest {

    @Autowired
    ProductDataAccessService repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
    @Rollback
    @Test
    public void createInventoryData() {
        // track initial state in test database:
        final int count = countRowsInTable("Product");
        List<Member> list = new ArrayList<>();
        Member member1 = new Member("FM-HKTV999","Washing Machine",999);
        Member member2 = new Member("FM-HKTV987","Machine",111);
        list.add(member1);
        list.add(member2);
        repository.addProductDetailFromCSV(list);

        assertNumUsers(list.size());
    }

    protected int countRowsInTable(String tableName) {
        return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
    }

    protected void assertNumUsers(int expected) {
        assertEquals("Number of rows in the [Member] table.", expected, countRowsInTable("Product"));
    }
}
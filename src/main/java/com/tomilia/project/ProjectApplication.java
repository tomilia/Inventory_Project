package com.tomilia.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(ProjectApplication.class, args);

    }
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("Creating tables...");
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS Inventory;");
        jdbcTemplate.execute("use Inventory");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Product (" +
                "p_code VARCHAR(16) NOT NULL," +
                "p_name VARCHAR(255) NOT NULL," +
                "p_weight int NOT NULL, PRIMARY KEY (p_code)" +
                "); ");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Prod_Loc (p_code VARCHAR(16) NOT NULL, " +
                "amount int NOT NULL, location CHAR(4) NOT NULL, " +
                "PRIMARY KEY(p_code,location)," +
                " FOREIGN KEY (p_code) REFERENCES Product(p_code) ON DELETE CASCADE" +
                "); ");
    }

}

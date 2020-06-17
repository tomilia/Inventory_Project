# Inventory_Project


## To initialize the database in MySQL:

1. Run MySQL Server on Port 3306 and create database named "Inventory";

2. Clone this project

3. cd to `<your_source_path>/Inventory_Project`

4. Navigate to `src/resources/application.properties`

5. Change the MySQL username & password under `spring.datasource.username` and `spring.datasource.password` respectively
   
  **Alternative:**
1. Clone MySQL image from docker hub : `docker pull arsenel12/mysql-spring:first`

2. Run `docker run -p 3306:3306 -d --name=mysql-spring arsenel12/mysql-spring:first` to mount MySQL Server to Port 3306

3. Run `docker exec -it mysql-spring bash -c "mysql -uroot -pPassw0rd! -e \" CREATE DATABASE Inventory; \""` to create   Inventory Database

4. The database is now ready!


## To run the Spring Application:

1. Clone this project
2. cd to `<your_source_path>/Inventory_Project`
3. Run `mvn install`
4. Run `cd target && java -jar project-0.0.1-SNAPSHOT.jar`
5. Open Browser and navigate to `localhost:8080` :sparkles: 

### CSV Uploading Mechanism and sample data
* The csv data are uploaded to the Web App via UI
  * Sample data are presented namely "data.csv" (for Product Details) and "product_quantity.csv" (for Product Quantity)
  * Assume no repeating product code  will be appeared in same csv, otherwise the latter will be ignored.
* "data.csv" accepts rows from 3 columns namely "Code","Name" & "Weight".
* "product_quantity.csv" accepts rows from 3 columns namely "Code","Amount" & "Location".

## Daily Process

- 15/6: Build & Config the Spring Application, dividing data access object, services and api controller, mapping row of Objects into Database, testing asynchronous call to render from Controller result in Thymeleaf template.

- 16/6: Creating CSV Reader and notify CSV "BOM" bug, fix the bugs and create test for JDBC Insertion.
- 17/6: Create docker image and push & Refactor the name (as I wrongly start the project with DAO named "Member"...)
   

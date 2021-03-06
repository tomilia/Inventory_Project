# Inventory_Project

*** Please initialize the database before building the Web Application ***	

## To initialize the database in MySQL:

1. Run MySQL Server on Port 3306

2. Clone this project

3. cd to `<your_source_path>/Inventory_Project`

4. Navigate to `src/resources/application.properties`

5. Change to existing properties (i.e. `spring.datasource.username=<your_username>`, `spring.datasource.password=<your_password>` and `spring.datasource.url = jdbc:mysql://localhost:3306/<Your_Database_Name>` respectively)
  
  **Alternative:**
1. Clone MySQL image from docker hub : `docker pull arsenel12/mysql-spring:first`

2. Run `docker run -p 3306:3306 -d --name=mysql-spring arsenel12/mysql-spring:first` to mount MySQL Server to Port 3306

3. Run `docker start mysql-spring` to start the container

4. Run `docker exec -it mysql-spring bash -c "mysql -uroot -p -e \" CREATE DATABASE Inventory; \""` to create Inventory Database

5. Enter Passw0rd! (Might require several trials)

6. The database is now ready!


## To run the Spring Application:

1. cd to `<your_source_path>/Inventory_Project`
2. Run `mvn install`
3. Run `cd target && java -jar project-0.0.1-SNAPSHOT.jar`
4. Open Browser and navigate to `localhost:8080` :sparkles: 

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
   

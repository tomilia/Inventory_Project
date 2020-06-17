# Inventory_Project


## To initialize the database in MySQL:

1. Run MySQL Server on Port 3306 and create database named "Inventory";

2. Navigate to `src/resources/application.properties`

3. Change the MySQL username & password under `spring.datasource.username` and `spring.datasource.username` respectively
   
  **Alternative:**
1. Clone MySQL image from docker hub : `docker pull arsenel12/mysql-spring:first`

2. Run `docker run -p 3306:3306 -d --name=mysql-spring arsenel12/mysql-spring:first` to mount MySQL Server to Port 3306

3. Run `docker exec -it mysql-spring bash -c "mysql -uroot -pPassw0rd! -e \" CREATE DATABASE Inventory; \""` to create   Inventory Database

4. The database is now ready!


###

## To run the Spring Application:

1. Clone this project
2. cd to `<your_source_path>/project`
3. Run `mvn install`
4. Run `cd target && java -jar project-0.0.1-SNAPSHOT.jar`
5. Open Browser and navigate to `localhost:8080` :sparkles: 

### Sample data:
* data.csv imports the Products detail (i.e. p_code,p_name and p_weight) accepts format with 3 columns namely "Code","Name","Weight" uploading to the web application by UI manually.
* product_quantity.csv imports the amount & location to store referring to the product code uploading to the web application by UI manually.

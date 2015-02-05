# README #

### What is this repository for? ###

#### Quick summary ####
This project is for creating a business desktop aplication using JavaEE and JavaFX. It's goal is provide a product for a specific company(the client). The project is free of costs so the code is available as open source.



### How do I get set up? ###

#### Summary of set up ####

The project uses Glassfish 4.1 as an application server and PostgreSQL as a database vendor.

#### Configuration ####
Glassfish configuration:
1. Copy postgresql-x.x-xxx.jdbc jar file into domain */lib* folder (located in glassfish installation folder under *domains/domain1*; If you installed glassfish with NetBeans the domain1 folder path is:* C:/Users/User_name/AppData/Roaming/NetBeans/version/config/Glassfish/.../domain1/lib*)
2. Start glassfish domain console and open JDBC connection pool
3. Create a new connection pool:
- Pool Name: *FakturyPool*
- Resource type: *javax.sql.DataSource*
- Vendor: *Postgresql*
4. Click Next and add properties: 
- User: *xxxxxx*
- Password: *xxxxx*
- ServerName: *localhost*
- PortNumber: *5432*
- DatabaseName: *FakturyDB*
5. Click finish and check connection by clicking 'ping'
6. Create JDBC Resource:
- JNDI Name: *jdbc/assenFakturyDB*
- Pool Name: *FakturyPool*
7. Click ok and finish

#### Database configuration ####
Create a new role in PostgreSQL(name, password). Then create a new database called FakturyDB and grant all options to new role.

#### How to run tests ####
Tests run using Arquillian and remote glassfish server. To run tests you need to start your server first. 

* Deployment instructions

### Contribution guidelines ###

#### Writing tests ####
Test should use Arquillian and by deployed to remote glassfish server for an EJB beans. 
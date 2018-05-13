# Contact API
This is a Gradle project and Intellij IDEA was used for the development purpose. 

This project is a Spring Boot application having different endpoints to perform following operations:
1. Create a contact record
2. Retrieve a contact record
3. Update a contact record
4. Delete a contact record
5. Search for a record by email or phone number
6. Retrieve all records from the same state or city

H2 in-memory database is used to store contact records. DDL for CONTACT_INFO table is present in __"ContactAPI\src\main\resources\db\schema.sql"__.

API Specification can be access over "http://localhost:8080/swagger-ui.html" link when application is running. API specification is generated using Swagger API.

Some of important classes description
1. ContactApiApplication is the Spring Boot config class, 
2. ContactController is the controller class with endpoint,
3. DataSourceConfig is H2 database config class
4. ContactAPIExecptionProcessor handles any exception thrown by application
5. ContactService in the business layer interface and ContactServiceImpl is the implementing class 
6. ContactRepo is the data access layer interface and ContactRepoImpl is the implementing class
7. ContactRepoHelper contains all SQL queries
8. Contact and Address are the domain object classes

## Deployment and Testing
### Deployment
Run the below command:

* _java -jar contactapi-1.0.0-RC.war_
* From IDE, run the main() method of the ContactApiApplication class

It will start the application on localhost at 8080 port.

### Testing
Hit "http://localhost:8080/swagger-ui.html" to access the application's swagger documentation. On clicking "__contact-conroller__" option, it will open application specification along with sample test data. Click on "__try it out__" of respective endpoints to test the application functionality, clicking "__execute__" button will perform the action.

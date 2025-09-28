# springboot-interview-assignment

1) Prerequisites:
- Java 17+
- Gradle 8+
- Local MSSQL server with a database named TESTDB. Create DB and make sure port 1433 is reachable.
- Update datasource username/password in application.yml


2) Build & Run:
./gradlew clean build
java -jar build/libs/springboot-interview-assignment-0.0.1-SNAPSHOT.jar


3) DB Schema:
- Either let Hibernate create the `users` table (spring.jpa.hibernate.ddl-auto=update) or run db-init.sql against TESTDB.


4) Logging:
- Request & response logs are written to logs/request-response.log (rotates daily). Full application logs in logs/interview-assignment.log if enabled.


5) Postman:
- Import postman.json in Postman and test endpoints.


6) Features implemented per assignment:
- Project structure with Controller/Service/Repository/Entity
- REST APIs for create/update/get/list (pagination)
- @Transactional on methods handling INSERT/UPDATE/GET where requested
- Request/Response logging into a separate file
- External nested API call (proxy) demonstrating calling a 3rd party endpoint
  

## KU Leuven - Distributed Applications - Final Project
### Grocery Store 
#### Bartosz Sobkowiak r0768717


## System Requirements
- Netbeans IDE 8.0.2
- GlassFish Server 4.1
- JDBC Derby Database (Netbeans in-built)
- JDK 1.8
- Firefox v.68 or newer / Chrome v.76 or newer
- JUnit (optional)
- Netbeans CSS connector (optional)


## Project Description


## How to run app
1. Download ZIP archive from Github
2. Extract archive
3. Open Netbeans IDE -> File -> Open Project -> open extracted directory as Netbeans project
4. Load all dependecies (this process usually starts automatically)
(You may see some warnings with JUnit or with syntax-check in .css files but they do not affect project build and deployment)
5. Set server to Glassfish 4.1, JDK to JDK_1.8, database to jdbc:derby/sample (these steps usually are done automatically) 
6. Clean and build
7. Run project
8. In case of 'Inconsistent state' errors - clean project builds and restart IDE
9. Enjoy!


## Project Expectations

#### EJB
- [x] At least one stateless 
- [x] stateful EJB session bean
- [x] At least one Singleton bean: 
- [x] At least one interceptor
- [x] At least one timer
- [x] A MDB (Message Driven Bean) with a Message Queue (and some code that posts a message in the queue)
- [x] A SOAP web service
- [x] A RESTFul web service

#### JPA
- [x] A few entity beans 
- [x] At leas one one-to-many 
- [x] At least one many-to-many relationship
- [x] Corresponding tables
- [x] Some "special" JPA annotations (make a selection yourself)
- @Enumerated

#### Web-client
- [x] One Servlet
- [x] One Filter
- [x] Framework: the choice is up to you
- [x] Page flow
- [x] Form & validation & business logic
- [x] Web service clients
- [x] One client for your own web services
- [x] One SOAP client
- [x] One client for the RESTFul service (Json or XML)



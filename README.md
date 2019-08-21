## KU Leuven - Distributed Applications - Final Project
#### Grocery Store 
#### Bartosz Sobkowiak r0768717


### System Requirements
- Netbeans IDE 8.0.2
- GlassFish Server 4.1
- JDBC Derby Database (Netbeans in-built)
- JDK 1.8
- Firefox v.68 or newer / Chrome v.76 or newer
- JUnit (optional)
- Netbeans CSS connector (optional)


### Project Description


### How to run an app
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


### Project Expectations

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
- [x] A few entity beans: **ejb.BankAccount, ejb.User, ejb.Basket, ejb.Item, ejb.Countable, ejb.Uncountable
- [x] At leas one one-to-many: **User - Basket, Basket - Item
- [x] At least one many-to-many relationship: **User - BankAccount
- [x] Corresponding tables **USER_ACCOUNT - BANKS - USER_BANKACCOUNT 
- [x] Some "special" JPA annotations (make a selection yourself)
  - @Enumerated
  - @GeneratedValue
  - @DiscriminatorValue
  - @EJB
  - @Resource
  - @Table
  - etc...

#### Web-client
- [x] At least One Servlet: **web.msg.UserMsg and all from web.msg package , StartPage and others from web package
- [x] One Filter: **ItemValidationFilter
- [x] Framework: the choice is up to you: **Bootstrap-4.1 with HTML/CSS/JS/Jquery-1.11 support
- [x] Page flow: **page flow available
- [x] Form & validation & business logic **JavaScript validation and ItemValidationFilter
- [x] One client for your own web services: **client available
- [x] One SOAP: **soap.UserSoapWebService and others from soap package
- [x] One client for the RESTFul service (Json or XML): **service.RestClient client , service.UserFacadeREST and rest from service package



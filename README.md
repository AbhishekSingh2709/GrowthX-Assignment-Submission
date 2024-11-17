# PROJECT OVERVIEW
*The project is a simple tool to help students submit their assignments online and make it easier for teachers to review and manage them. It saves time and makes the whole process smooth for everyone involved.

## FEATURES
* User Management: Handle user registration and authentication.

* Assignment Submission: Allow users to upload assignments and track submissions.

* Admin Dashboard: Enable administrators to review, approve, or reject assignments.

* Error Handling: Robust error handling mechanisms to ensure a smooth user experience.

## TECH-STACK
* Programming Languages:- Java

* Frameworks:- Spring Boot

* Tools:- Maven, Postman

* Database:- MySQL

* Other:- REST API, Apache Tomcat

## PROJECT STRUCTURE
- *src/*
  - *main/*
    - *java/*
      - *com/*
        - *assignmentSubmission/*
          - *configuration/*  
            - Holds configuration files for application setup(e.g- Security, JwtConfig ).
          - *controller/*  
            - Handles API requests.
          - *service/*  
            - Contains business logic.
            - Defines the data structure.
          - *repository/*  
            - Manages database interactions.
          - *entity/*  
            - Contains entity classes representing database tables.
          - *payload/*  
            - Includes request and response objects for API communication.
    - *resources/*
      - *application.properties*  
        - Configuration settings.
      - *static/*  
        - Frontend files (Null).
  - *test/*  
    - Contains API Testing using Postman

## API END-POINTS
### User Endpoints
- *POST* /user/register  
  - Register a new user.
  - EndPoint :- http://localhost:8080/api/v1/users-registration/userInfo

- *POST* /user/login  
  - User authentication.
  - EndPoint :- http://localhost:8080/api/v1/users-registration/userLogin

- *POST* /user/assignments  
  - Submit an assignment.
  - EndPoint :- http://localhost:8080/api/v1/users-registration/submittingAssignment?userid= "put user id here"

- *GET* /user/fetch admins  
  - Retrieve all admins present in database.
  - EndPoint :- http://localhost:8080/api/v1/users-registration/fetchAdmins

### Admin Endpoints
- *POST* /admin/register  
  - Register a new admin
  - EndPoint :- http://localhost:8080/api/v1/Admin/adminRegister

- *POST* /admin/login 
  - admin authentication
  - EndPoint :- http://localhost:8080/api/v1/Admin/adminLogin

- *GET* /admin/tagged assignment 
  - admin can fetch all assgnment tagged to them
  - EndPoint :- http://localhost:8080/api/v1/Admin/fetchAssignments?adminName= "Enter Admin name for tagged assignment"

- *POST* /admin/accept 
  - Approve an assignment.
  - EndPoint :- http://localhost:8080/api/v1/Admin/updateStatusAccept?id= "Enter Assignment id"

- *POST* /admin/reject  
  - Reject an assignment.
  - EndPoint :- http://localhost:8080/api/v1/Admin/updateStatusRejected?id= "Enter Assignment id"

## Workflow
   1.Starting the Application:
   * Run the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse). I have used IntelliJ JDK 17
   * Create A scehma in MySQL workbench as **assignmentsubmission** and the conevtivity with JDBC helps in properties file will be like **jdbc:mysql://localhost:3306/assignmentsubmission**
   * On Project startup, all necessary database tables will be created automatically in the MySQL database configured in the application properties.

   2.Testing API Endpoints: 
   * Use Postman tool to test the provided endpoints.
   * Enter the specified endpoint URL and provide the required input data in JSON format in the request body or in end points accordingly.
   * Upon executing the request, the provided data will be processed and stored in the corresponding table in the database, and can also fetch the data from database according to the endpoint you run.

## JWT Token-Based Security Workflow
   1.User Authentication:-
   * After successful registration or login, the server generates a JWT token and returns it in the response.This token serves as proof of authentication.
   * The token encodes user information and is signed by the server using a secret key to ensure its integrity and security.

   2.Using the JWT Token:-
   * To access some endpoints that require authentication, the JWT token must be included in the request header under the Authorization Section
   * the token is passed as Bearer Token.

   3.Token Validation:-
   * When the request reaches the server, the backend verifies the token's validity and decodes the user information.
   * If the token is valid, the server processes the request and grants access to the endpoint.
   * If the token is invalid, expired, or missing, the server denies access and returns an authentication error or forbidden.
   

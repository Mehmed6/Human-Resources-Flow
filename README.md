# Human Resources Flow
HRFlow is an end-to-end HR management solution that helps organizations efficiently manage employee records, salaries, leave requests, and HR processes. With role-based access control and Spring Security, it provides a secure and robust platform for managing human resources.
The system allows users to securely register, log in, and use JWT-based authentication and authorization mechanisms. Once logged in, users can perform actions with a JWT token. If the token expires, a new token can be obtained using a refresh token.
The project leverages tools like MapStruct and Lombok to simplify coding and enhance development efficiency.

## Features
* **User Registration:** Users can register with their information and save it to the database.
* **User Login:** Users can log in with their email and password.
* **Role-Based Access Control:** The system has two roles: USER and ADMIN. Users can perform operations based on their roles.
* **JWT Token Authentication and Authorization:** JWT tokens are used for secure authentication and authorization.
* **Refresh Token:** A refresh token is used to obtain a new JWT token when the current token expires.
* **Spring AOP:** Aspect-Oriented Programming is used to log user operations.
* **Lombok:** Lombok is used to reduce boilerplate code.
* **PostgreSQL:** PostgreSQL is used as the database.
* **Mapstruct:** Mapstruct is used to simplify object mapping.
* **Exception Handling:** Custom exception handling is implemented to handle errors.
* **Spring Security:** Spring Security is used for authentication and authorization.
* **Swagger:** Swagger is used to document the API endpoints.

## 💻  Technologies Used

* Java 17
* Spring Boot
* Spring Security
* JWT Token Authentication and Authorization for user operations
* Spring AOP (Aspect-Oriented Programming)
* Java Reflection API
* BufferedWriter
* Lombok
* PostgreSQL
* Mapstruct
* Swagger
* Docker & Docker Compose

## 🔗  Services

* **RegisterService:** Handles user registration operations, including creating new user accounts and saving user data to the database.
* **LoginService:**  Handles user authentication, verifying credentials and generating JWT tokens for users upon successful login.
* **LeaveRequestService:** Handles employee leave requests, including submitting leave requests, approving/rejecting them, and fetching leave requests by status, employee, or date.
* **SalaryService:** Manages salary-related operations, such as adding, updating, fetching salaries, and filtering them by salary amount.
* **PositionService:** Manages position-related operations, such as creating, updating, and deleting positions, as well as fetching position details by ID or title.
* **DepartmentService:** Manages department-related operations, such as creating, deleting, and fetching department details by name or ID.
* **EmployeeService:** Manages employee-related operations, such as adding new employees, fetching employee details by ID or username, and filtering employees by department or position.
* **LongEntryService:** Logs system operations, keeping track of actions performed by users, and saving logs in the database.
* **RefreshTokenService:** Manages refresh tokens, allowing the creation of new access and refresh tokens, and invalidating old ones.

# 🌐 API Endpoints

## RegisterController

* `POST -> http://localhost:6767/register`
  {
      "username": "username",
      "firstName": "firstname",
      "lastName": "lastname",
      "email": "email",
      "password": "password",
      "phone": "phone",
      "departmentId": departmentId,
      "positionId": positionId
  }
  Users submits this request body to perform the registration process.

## LoginController

* `POST -> http://localhost:6767/auth/login`
  {
      "username": "username",
      "password": "password"
  }
  Users submits this request body to perform the login process.

* `POST -> http://localhost:6767/auth/login/admin`
  {
  "username": "username",
  "password": "password"
  }
  Admins submits this request body to perform the login process.

## DepartmentController

* `POST -> http://localhost:6767/api/admin/department/save`
  {
      "departmentName": "departmentName",
      "departmentDescription": "departmentDescription"
  }
  Admins submits this request body to save a new department.

* `GET -> http://localhost:6767/api/admin/department/find/id/{departmentId}`
  Finds the related department using the departmentId parameter and throws an error if not found.
* `GET -> http://localhost:6767/api/admin/department/name/{departmentName}`
  Finds the related department using the departmentName parameter and throws an error if not found.
* `GET -> http://localhost:6767/api/admin/department/find/all`
  Only admins can retrieve a list of all departments.
* `GET -> http://localhost:6767/api/admin/department/delete/{departmentId`
  Only admins can delete a department using the specified departmentId.

## EmployeeController

* `POST -> http://localhost:6767/api/employee/save`
  {
      "username": "username",
      "firstName": "firstname",
      "lastName": "lastname",
      "email": "email",
      "password": "password",
      "phone": "phone",
      "departmentId": departmentId,
      "positionId": positionId
  }
  Admins submits this request body to save a new employee.
* `GET -> http://localhost:6767/api/employee/find/id/{employeeId}`
  Finds the related employee using the employeeId parameter and throws an error if not found.
* `GET -> http://localhost:6767/api/employee/find/username/{username}`
  Finds the related employee using the username parameter and throws an error if not found.
* `GET -> http://localhost:6767/api/employee/find/all`
  Only admins can retrieve a list of all employees.
* `GET -> http://localhost:6767/api/employee/find/department/{departmentId}`
  Retrieves a list of employees associated with the specified departmentId.
* `GET -> http://localhost:6767/api/employee/find/position/{positionId}`
  Retrieves a list of employees associated with the specified positionId.
* POST -> http://localhost:6767/api/employee/find/leave/date
  {
      "date": "yyyy-MM-dd"
  }
  Only admins can retrieve a list of employees who are on leave on the specified date. The request body should contain the date in the format yyyy-MM-dd.

## LeaveRequestController

* `POST -> http://localhost:6767/api/employee/leave/request/save`
  {
      "username": "username",
      "leaveReason": "leaveReason",
      "startDate": "yyyy-MM-dd",
      "endDate": "yyyy-MM-dd"
  }
  Users submit this request body to save a new leave request.
* `GET -> http://localhost:6767/api/employee/leave/request/find/all/username/{username}`
  Finds all leave requests associated with the specified username.
* `GET -> http://localhost:6767/api/employee/leave/request/find/all`
  Only admins can retrieve a list of all leave requests.
* `GET -> http://localhost:6767/api/employee/leave/request/find/all/status/{status}`
  Retrieves a list of leave requests based on the specified status.
* `PATCH -> http://localhost:6767/api/employee/leave/request/approved/{leaveRequestId}`
  Only admins can approve a leave request using the specified leaveRequestId.
* `PATCH -> http://localhost:6767/api/employee/leave/request/rejected/{leaveRequestId}`
  Only admins can reject a leave request using the specified leaveRequestId.

## PositionController

* `POST -> http://localhost:6767/api/position/save`
  {
      "title": "title",
      "description": "description",
      "department": "department"
  }
  Admins submits this request body to save a new position.
* `GET -> http://localhost:6767/api/position/find/id/{positionId}`
  Finds the related position using the positionId parameter and throws an error if not found.
* `GET -> http://localhost:6767/api/position/find/title/{positionTitle}`
  Finds the related position using the positionTitle parameter and throws an error if not found.
* `GET -> http://localhost:6767/api/position/find/all`
  Only admins can retrieve a list of all positions.
* `DELETE -> http://localhost:6767/api/position/delete/{positionId}`
  Only admins can delete a position using the specified positionId.

## RefreshTokenController

* `POST -> http://localhost:6767/api/refreshToken`
  {
      "refreshToken": "refreshToken"
  }
  Users submit this request body to obtain a new JWT tokens using the refresh token.

## SalaryController

* `POST -> http://localhost:6767/api/salary/save`
  {
      "salary": salary,
      "salaryType": "salaryType",
      "paymentDate": "yyyy/MM/dd HH:mm",
      "employeeId": employeeId
  }
  Admins submits this request body to save a new salary.
* `GET -> http://localhost:6767/api/salary/find/all`
  Only admins can retrieve a list of all salary details.
* `GET -> http://localhost:6767/api/salary/find/employee/salaries/{employeeId}`
  Only admins can retrieve the salary details of the employee specified by employeeId.
* `GET -> http://localhost:6767/api/salary/find/type/{salaryType}`
  Only admins can retrieve salary details based on the specified salaryType.
* `GET -> http://localhost:6767/api/salary/find/between`
  Only admins can retrieve salary details for amounts between the specified min and max values.
* `GET -> http://localhost:6767/api/salary/find/greater`
  Only admins can retrieve salary details greater than the specified value.
* `GET -> http://localhost:6767/api/salary/find/less`
  Only admins can retrieve salary details less than the specified value.

# NOTES

### AdminInitializer
This class automatically creates an admin user with the username `admin` and password `admin` at the beginning of the program

### SecurityUtil
The SecurityUtil class provides utility methods for retrieving information related to the current authenticated user within the application. Specifically, it provides a method to obtain the username of the currently authenticated user from the security context.

### LoggingAspect
This class is responsible for logging method executions in the controller and service layers of the application using Aspect-Oriented Programming (AOP). It logs HTTP request details before controller methods are executed, logs successful method executions after they return, and logs exceptions when they occur. Logs are saved to a file named ActivityLog.log under the logs directory. This class helps monitor the application's flow and detect any issues by recording method executions and exceptions.

### GlobalExceptionHandler 
The GlobalExceptionHandler class is a centralized exception handler that catches and processes various types of exceptions thrown throughout the application. It ensures consistent and structured error responses for the client, regardless of where the exception is thrown.
* `Handles Custom Exceptions (ApiException):` This class handles ApiException by extracting error details from the exception and returning a structured response with error codes, messages, and the request path.
* `Handles Validation Errors (MethodArgumentNotValidException):` If the request fails validation (e.g., required fields are missing or data formats are incorrect), it catches the validation exception and returns a detailed error message with all validation errors.
* `Global Exception Handler:`Catches any unexpected exceptions that are not explicitly handled. It provides a generic error response with the exception's class name and message.

### Docker & Docker Compose:
* The project includes a `docker-compose.yml` file that allows you to run the project in a Docker container.
* To run the project with Docker and Docker Compose, you can easily start the application by using the Docker Compose file. After cloning the project, run the `docker-compose up --build` command to start the application along with all dependencies.

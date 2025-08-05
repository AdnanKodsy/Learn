# Spring Boot CRUD Application – Class Explanations

This document explains the purpose of each class in the project and why it is used.  
All database operations use raw SQL via JdbcTemplate, following the project instructions.

---

## 1. `CrudAppApplication.java`
**Location:** `src/main/java/com/example/crudapp/CrudAppApplication.java`

**Purpose:**  
- This is the main entry point for the Spring Boot application.
- The `@SpringBootApplication` annotation enables component scanning, auto-configuration, and configuration support.
- The `main` method launches the application.

---

## 2. `User.java`
**Location:** `src/main/java/com/example/crudapp/model/User.java`

**Purpose:**  
- Represents the User entity (data model) with fields: `id`, `name`, `email`, and `age`.
- Used to transfer user data between layers (controller, service, repository).
- Contains constructors, getters, setters, and a `toString` method.
- No ORM or JPA annotations are used; it is a plain Java object.

---

## 3. `UserRepository.java`
**Location:** `src/main/java/com/example/crudapp/repository/UserRepository.java`

**Purpose:**  
- Handles all database operations for the User entity using raw SQL and `JdbcTemplate`.
- Annotated with `@Repository` to indicate it's a data access component.
- Provides methods for:
  - Creating and updating users (`save`)
  - Retrieving all users (`findAll`)
  - Finding users by ID (`findById`)
  - Searching users by name (`findByNameContaining`)
  - Deleting users (`deleteById`)
  - Checking if a user exists (`existsById`)
  - Counting users (`count`)
- Uses a `RowMapper` to map SQL result sets to `User` objects.

---

## 4. `UserService.java`
**Location:** `src/main/java/com/example/crudapp/service/UserService.java`

**Purpose:**  
- Contains business logic for user operations.
- Annotated with `@Service` to indicate it's a service component.
- Calls repository methods to perform CRUD operations.
- Handles logic for:
  - Creating/updating users (`saveUser`)
  - Retrieving all users (`getAllUsers`)
  - Finding users by ID (`getUserById`)
  - Searching users by name (`searchUsersByName`)
  - Updating users (`updateUser`)
  - Deleting users (`deleteUser`)
  - Checking if a user exists (`userExists`)
  - Counting users (`getUserCount`)
- Ensures business rules are separated from web and data access logic.

---

## 5. `UserController.java`
**Location:** `src/main/java/com/example/crudapp/controller/UserController.java`

**Purpose:**  
- Exposes REST API endpoints for user CRUD operations.
- Annotated with `@RestController` and `@RequestMapping("/api/users")`.
- Handles HTTP requests and responses for:
  - Creating a user (`POST /api/users`)
  - Retrieving all users (`GET /api/users`)
  - Retrieving a user by ID (`GET /api/users/{id}`)
  - Searching users by name (`GET /api/users/search?name=...`)
  - Updating a user (`PUT /api/users/{id}`)
  - Deleting a user (`DELETE /api/users/{id}`)
  - Getting user count (`GET /api/users/count`)
- Uses `ResponseEntity` for flexible HTTP responses.
- Delegates business logic to the `UserService`.

---

## 6. `UserControllerTest.java`
**Location:** `src/test/java/com/example/crudapp/controller/UserControllerTest.java`

**Purpose:**  
- Contains unit tests for the `UserController` REST API.
- Uses `@WebMvcTest(UserController.class)` to test only the controller layer.
- Uses `MockMvc` to simulate HTTP requests and verify responses.
- Mocks the `UserService` dependency to isolate controller tests.
- Tests endpoints for getting all users, getting a user by ID, and creating a user.

---

## 7. `application.properties`
**Location:** `src/main/resources/application.properties`

**Purpose:**  
- Configures application settings, including:
  - H2 in-memory database connection
  - H2 console access
  - SQL schema and data initialization
  - Logging levels
  - Server port

---

## 8. `schema.sql`
**Location:** `src/main/resources/schema.sql`

**Purpose:**  
- Defines the SQL schema for the `users` table.
- Ensures the table is created with the correct columns and constraints when the application starts.

---

## 9. `data.sql`
**Location:** `src/main/resources/data.sql`

**Purpose:**  
- Inserts sample user data into the database at startup.
- Useful for development and testing.

---

## Summary Table

| Class/File                | Purpose                                                        |
|---------------------------|----------------------------------------------------------------|
| CrudAppApplication.java   | Main entry point for the Spring Boot app                       |
| User.java                 | User data model (POJO)                                         |
| UserRepository.java       | Data access layer using raw SQL and JdbcTemplate               |
| UserService.java          | Business logic layer                                           |
| UserController.java       | REST API controller                                            |
| UserControllerTest.java   | Unit tests for the controller layer                            |
| application.properties    | Application and database configuration                         |
| schema.sql                | Database schema definition                                     |
| data.sql                  | Sample data for
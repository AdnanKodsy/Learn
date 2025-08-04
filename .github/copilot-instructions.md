<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->

# Spring Boot CRUD Application Instructions

This is a Spring Boot application that performs CRUD operations using raw SQL queries instead of ORM frameworks like JPA/Hibernate.

## Key Technologies:
- Spring Boot 3.2.0
- JdbcTemplate for database operations
- H2 in-memory database
- Maven for dependency management
- Java 17

## Architecture:
- **Model**: User entity with id, name, email, and age fields
- **Repository**: UserRepository using JdbcTemplate with raw SQL queries
- **Service**: UserService containing business logic
- **Controller**: REST API endpoints for CRUD operations

## Important Notes:
- No ORM frameworks are used - all database operations use raw SQL
- H2 database is configured for development/testing purposes
- Database schema is initialized via schema.sql
- Sample data is loaded via data.sql
- All SQL queries are written manually using JdbcTemplate

## API Endpoints:
- GET /api/users - Get all users
- GET /api/users/{id} - Get user by ID
- POST /api/users - Create new user
- PUT /api/users/{id} - Update user
- DELETE /api/users/{id} - Delete user
- GET /api/users/search?name={name} - Search users by name
- GET /api/users/count - Get total user count

## Database Access:
- H2 Console available at: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (empty)

When generating code, ensure all database operations use raw SQL queries through JdbcTemplate.

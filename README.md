# Spring Boot CRUD Application

A simple Spring Boot application demonstrating CRUD operations using raw SQL queries without ORM frameworks.

## Features

- **No ORM**: Uses JdbcTemplate with raw SQL queries instead of JPA/Hibernate
- **REST API**: Complete CRUD operations via RESTful endpoints
- **H2 Database**: In-memory database for development and testing
- **Maven**: Dependency management and build tool
- **Spring Boot 3.2.0**: Latest Spring Boot features with Java 17

## Project Structure

```
src/
├── main/
│   ├── java/com/example/crudapp/
│   │   ├── CrudAppApplication.java      # Main application class
│   │   ├── controller/
│   │   │   ├── OrderController.java     # Order management endpoints
│   │   │   ├── OrderItemController.java # Order item management endpoints
│   │   │   ├── ProductController.java   # Product management endpoints
│   │   │   └── UserController.java      # User management endpoints
│   │   ├── dto/
│   │   │   ├── OrderItemRequest.java    # Order item DTO for API requests
│   │   │   └── OrderRequest.java        # Order DTO for API requests
│   │   ├── model/
│   │   │   ├── Order.java               # Order entity
│   │   │   ├── OrderItem.java           # Order item entity
│   │   │   ├── Product.java             # Product entity
│   │   │   └── User.java                # User entity
│   │   ├── repository/
│   │   │   ├── OrderItemRepository.java # Order item data access with raw SQL
│   │   │   ├── OrderRepository.java     # Order data access with raw SQL
│   │   │   ├── ProductRepository.java   # Product data access with raw SQL
│   │   │   └── UserRepository.java      # User data access with raw SQL
│   │   └── service/
│   │       ├── OrderItemService.java    # Order item business logic
│   │       ├── OrderService.java        # Order business logic
│   │       ├── ProductService.java      # Product business logic
│   │       └── UserService.java         # User business logic
│   └── resources/
│       ├── application.properties       # Configuration
│       ├── schema.sql                   # Database schema
│       └── data.sql                     # Sample data
└── test/
    └── java/com/example/crudapp/
        └── controller/
            └── UserControllerTest.java  # Unit tests
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/search?name={name}` | Search users by name |
| GET | `/api/users/count` | Get total user count |
| POST | `/api/users` | Create new user |
| PUT | `/api/users/{id}` | Update existing user |
| DELETE | `/api/users/{id}` | Delete user |

## User Model

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "age": 30
}
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. **Clone and navigate to the project directory**

2. **Build the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application:**
   - API Base URL: `http://localhost:8080/api/users`
   - H2 Console: `http://localhost:8080/h2-console`

### H2 Database Console

Access the H2 database console for development and testing:

- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave empty)

## Example API Usage

### Create a User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","age":30}'
```

### Get All Users
```bash
curl http://localhost:8080/api/users
```

### Get User by ID
```bash
curl http://localhost:8080/api/users/1
```

### Update User
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"John Smith","email":"john.smith@example.com","age":31}'
```

### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

### Search Users
```bash
curl "http://localhost:8080/api/users/search?name=John"
```

## Testing

Run the tests using Maven:

```bash
mvn test
```

## Key Implementation Details

- **Raw SQL**: All database operations use `JdbcTemplate` with hand-written SQL queries
- **No ORM**: Deliberately avoids JPA/Hibernate to demonstrate raw SQL usage
- **RowMapper**: Custom row mapping for converting `ResultSet` to `User` objects
- **Transaction Management**: Spring's declarative transaction management
- **Error Handling**: Proper HTTP status codes and error responses

## Sample Data

The application includes sample users loaded on startup:

1. John Doe (john.doe@example.com, age 30)
2. Jane Smith (jane.smith@example.com, age 25)
3. Bob Johnson (bob.johnson@example.com, age 35)
4. Alice Brown (alice.brown@example.com, age 28)

## Configuration

The application uses H2 in-memory database configured in `application.properties`:

- **Database URL**: `jdbc:h2:mem:testdb`
- **Console enabled**: For development access
- **Schema initialization**: Automatic via `schema.sql`
- **Data initialization**: Sample data via `data.sql`

## Next Steps

- Add input validation
- Implement pagination for large datasets
- Add more complex queries and relationships
- Implement exception handling
- Add logging and monitoring
- Deploy to cloud platforms

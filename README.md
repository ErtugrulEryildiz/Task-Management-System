
# Task Management System

A robust Task Management System built using Java Spring Boot, designed to manage users, tasks, and task lists with complex data relationships. This application follows best practices in software development, ensuring high code quality, maintainability, and scalability.

## Features

- **User Management**: Register and authenticate users securely.
- **Task List Management**: Create, update, and delete task lists associated with a user.
- **Task Management**: Add, update, and delete tasks within task lists.
- **Data Integrity**: Cascading operations and orphan removal are implemented to maintain database consistency.
- **DTO Mapping**: Secure data transfer between layers using Data Transfer Objects (DTOs).
- **Comprehensive Testing**: Unit tests implemented using JUnit and Mockito.

## Technologies Used

- **Java Spring Boot**: Core framework for building the RESTful API.
- **Hibernate/JPA**: For ORM and managing complex entity relationships.
- **Lombok**: To reduce boilerplate code and improve readability.
- **Maven**: For project management and dependency resolution.
- **H2 Database**: Used for development and testing purposes.
- **JUnit & Mockito**: For unit testing and ensuring the reliability of the application.

## Installation and Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/task-management-system.git
   cd task-management-system
   ```

2. **Build the Project**
   Use Maven to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**
   The application will be available at `http://localhost:8080`.

## Database Configuration

The project is configured to use an H2 in-memory database for development and testing purposes. You can access the H2 console at `http://localhost:8080/h2-console` using the following credentials:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

## Testing

The project includes comprehensive unit tests to validate the functionality of the service and repository layers. To run the tests, use the following command:

```bash
mvn test
```

## Project Structure

The project follows a layered architecture:

- **Controller**: Handles HTTP requests and maps them to service methods.
- **Service**: Contains the business logic and interacts with repositories.
- **Repository**: Interfaces with the database using Spring Data JPA.
- **DTO**: Data Transfer Objects for transferring data between layers.

## Contributing

Contributions are welcome! If you have any ideas for improvements or find any bugs, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

Special thanks to the open-source community and the developers of the libraries and tools used in this project.

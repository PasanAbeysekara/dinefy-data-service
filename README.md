# Dinefy Data Service

<!-- Optional: Add a logo or banner image here -->
<!-- ![Dinefy Logo](path/to/your/logo.png) -->

Backend data service for the Dinefy restaurant table reservation system. This service manages all core data entities, business logic, and API endpoints related to organizations, properties (restaurants), users, menus, reservations, orders, and system configurations.
### Tech Stack


[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![JWT](https://img.shields.io/badge/JWT-000000?logo=jsonwebtokens&logoColor=white)](https://jwt.io/)
[![Log4j2](https://img.shields.io/badge/Log4j2-F06730?logo=apache&logoColor=white)](https://logging.apache.org/log4j/2.x/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white)](https://www.docker.com/)
<!-- Add more badges as needed, e.g., license, code coverage -->

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Database Setup](#database-setup)
  - [Application Configuration](#application-configuration)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Security](#security)
- [Asynchronous Operations](#asynchronous-operations)
- [Scheduled Tasks](#scheduled-tasks)
- [Testing](#testing)
- [Docker](#docker)
- [CI/CD](#cicd)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Dinefy Data Service is a robust backend system built using Java and the Spring Boot framework. It serves as the central hub for managing data and operations for the Dinefy platform, providing RESTful APIs for client applications (e.g., web frontends, mobile apps) to interact with.

Key responsibilities include:
*   User authentication and authorization.
*   Management of restaurants (properties), including their details, availability, and menus.
*   Handling of table reservations and customer orders.
*   Managing promotional offers and contracts.
*   Providing system-level configurations for various aspects of the platform.

## Features

*   **User Management:** Registration, login, profile management.
*   **Authentication & Authorization:** JWT-based security for API endpoints.
*   **Organization & Property Management:** CRUD operations for organizations and associated properties (restaurants).
*   **Location Services:** Management of geographical locations and states.
*   **Menu Management:** Creation and management of menus and menu items (choices).
*   **Reservation System:** Handling table reservations, availability checks, and booking confirmations.
*   **Order Processing:** Managing customer orders.
*   **Promotion Engine:** Creating and managing promotional offers.
*   **Contract Management:** Handling contracts with properties/partners.
*   **Availability Management:** Real-time or batch processing of property availability.
*   **System Configuration:** Managing system-wide entities like tags, facilities, event types, seat types, etc.
*   **HATEOAS Support:** Provides discoverable APIs through hypermedia links.
*   **Asynchronous Processing:** Utilizes message queues (e.g., for property data updates) and async executors for non-blocking operations.
*   **Scheduled Tasks:** Automated jobs for maintenance or periodic tasks (e.g., event updates).
*   **Internationalization:** Support for multiple languages (e.g., `messages_en.properties`).

## Tech Stack

*   **Language:** Java (Version specified in `pom.xml`, likely 11 or 17)
*   **Framework:** Spring Boot
    *   Spring MVC (for REST APIs)
    *   Spring Security (for authentication & authorization)
    *   Spring Data JPA (assumed, for database interaction)
    *   Spring HATEOAS
    *   Spring Messaging (for queue integration, e.g., RabbitMQ/Kafka - though specific broker not defined by structure alone)
*   **Build Tool:** Apache Maven
*   **Database:** MySQL (inferred from `.mwb` file and SQL scripts)
*   **Authentication:** JSON Web Tokens (JWT)
*   **Logging:** Log4j2
*   **Containerization:** Docker
*   **CI/CD:** GitHub Actions

## Project Structure

The project follows a standard Maven project structure:

```
dinefy-data-service/
│
├───.github/workflows/        # CI/CD pipeline configurations
├───.mvn/wrapper/             # Maven wrapper files
├───database/                 # Database schema (MWB) and SQL scripts
│   └───Scripts/              # DDL and DML scripts
├───src/
│   ├───main/
│   │   ├───java/com/thaprobit/resengine/ # Main application source code
│   │   │   ├───ano/            # Custom annotations
│   │   │   ├───app/            # Application lifecycle and core configurations
│   │   │   ├───config/         # Security (JWT, Web) configurations
│   │   │   ├───controller/     # REST API controllers and HATEOAS assemblers
│   │   │   │   ├───assembler/
│   │   │   │   ├───converters/
│   │   │   │   ├───service/    # Business logic services
│   │   │   │   ├───sys/        # System-level entity controllers
│   │   │   │   └───validator/  # Request validation logic
│   │   │   ├───dto/            # Data Transfer Objects (for authentication, simple responses)
│   │   │   ├───facade/         # Facade layer, including DTOs/Models for API responses
│   │   │   ├───interceptor/    # HTTP interceptors and exception handlers
│   │   │   ├───messaging/      # Message queue producers and consumers
│   │   │   └───schedular/      # Scheduled tasks
│   │   └───resources/          # Configuration files, static assets
│   └───test/                   # Unit and integration tests
│       ├───java/               # Test source code
│       └───resources/          # Test-specific configuration
│
├───.gitignore
├───codealike.json
├───Dockerfile                # Docker configuration
├───mvnw                      # Maven wrapper script (Linux/macOS)
├───mvnw.cmd                  # Maven wrapper script (Windows)
└───pom.xml                   # Maven project configuration
```

## Prerequisites

*   JDK 11 or higher (verify version in `pom.xml`)
*   Apache Maven 3.6+
*   MySQL Server (e.g., version 8.0)
*   Docker (optional, for containerized deployment)
*   An IDE like IntelliJ IDEA, Eclipse, or VS Code.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/PasanAbeysekara/dinefy-data-service.git
cd dinefy-data-service
```

### 2. Database Setup

1.  **Create a MySQL database:**
    For example, create a database named `dinefy_db`.
    ```sql
    CREATE DATABASE dinefy_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    ```
2.  **User and Privileges:** Create a user and grant necessary privileges.
    ```sql
    CREATE USER 'dinefy_user'@'localhost' IDENTIFIED BY 'your_password';
    GRANT ALL PRIVILEGES ON dinefy_db.* TO 'dinefy_user'@'localhost';
    FLUSH PRIVILEGES;
    ```
3.  **Configure Database Connection:**
    Update `src/main/resources/application.properties` with your MySQL database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/dinefy_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    spring.datasource.username=dinefy_user
    spring.datasource.password=your_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # JPA Properties (adjust as needed)
    spring.jpa.hibernate.ddl-auto=validate # or 'update' for initial setup, 'validate' for production
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    ```
4.  **Run SQL Scripts:**
    Execute the SQL scripts located in the `database/Scripts/` directory against your `dinefy_db` database. The recommended order might be:
    *   `TABLE.sql`
    *   `SEQUENCE.sql`
    *   `seat_type.sql`
    *   `property.sql`
    *   (Any other DML or seed data scripts)
    You can use a MySQL client (like MySQL Workbench, DBeaver, or the `mysql` command-line tool) to run these scripts.

### 3. Application Configuration

Review and adjust other properties in:
*   `src/main/resources/application.properties`: Main application settings, server port, JWT secrets, etc.
*   `src/main/resources/system.properties`: Custom system-level properties.
*   `src/main/resources/thread.properties`: Thread pool configurations.
*   `src/main/resources/log4j2.xml`: Logging configuration.

**Important:** For JWT, ensure you set a strong, unique secret in `application.properties`:
```properties
jwt.secret=your-super-strong-and-unique-secret-key-here
jwt.expiration.ms=86400000 # 24 hours
```

### 4. Running the Application

1.  **Build the project:**
    ```bash
    ./mvnw clean install
    ```
    (or `mvnw.cmd clean install` on Windows)

2.  **Run the application:**
    ```bash
    java -jar target/dinefy-data-service-*.jar
    ```
    Alternatively, you can run directly using the Maven Spring Boot plugin:
    ```bash
    ./mvnw spring-boot:run
    ```

The application will typically start on `http://localhost:8080` (or the port configured in `application.properties`).

## API Endpoints

The API endpoints are defined in the `com.thaprobit.resengine.controller` package. Key controllers include:

*   `AuthController`: For user registration and login.
*   `UserController`: For user-related operations.
*   `OrganizationController`: For managing organizations.
*   `PropertyController`: For managing restaurant properties.
*   `MenuController`: For menu and choice management.
*   `ReservationController`: For handling reservations.
*   `OrderController`: For order processing.
*   ...and others as per the file structure.

It is recommended to use a tool like Postman or Insomnia to interact with the APIs. Consider integrating Swagger/OpenAPI for live API documentation.

## Security

*   **Authentication:** Implemented using JWT. Users authenticate via the `/auth/login` endpoint to receive a token.
*   **Authorization:** Secured endpoints require a valid JWT in the `Authorization` header (Bearer token). Spring Security is configured in `com.thaprobit.resengine.config.SecurityConfig`.
*   **CORS:** Configured in `WebConfig` or `HangoutWebMvcConfig` to allow requests from specific origins.

## Asynchronous Operations

*   The service utilizes `PropAvailDataAsyncExecutor` for asynchronous execution of tasks, potentially for updating availability data without blocking main request threads.
*   Message queues (`PropertyQueueConsumer`, `PropertyQueueProducer`) are used for decoupled communication, likely for propagating updates or events related to properties. The specific message broker (e.g., RabbitMQ, Kafka) would be configured in `application.properties`.

## Scheduled Tasks

*   `EventScheduledTasks` in `com.thaprobit.resengine.schedular` contains tasks that run periodically, configured using Spring's scheduling capabilities (`@Scheduled`).

## Testing

Unit and integration tests are located in `src/test/java/`.
To run the tests:

```bash
./mvnw test
```

Test-specific configurations can be found in `src/test/resources/`.

## Docker

A `Dockerfile` is provided to build a Docker image for the application.

1.  **Build the Docker image:**
    ```bash
    docker build -t pasanabeysekara/dinefy-data-service .
    ```

2.  **Run the Docker container:**
    ```bash
    docker run -p 8080:8080 \
           -e SPRING_DATASOURCE_URL=jdbc:mysql://<your_mysql_host_or_ip>:3306/dinefy_db \
           -e SPRING_DATASOURCE_USERNAME=dinefy_user \
           -e SPRING_DATASOURCE_PASSWORD=your_password \
           -e JWT_SECRET=your-super-strong-and-unique-secret-key-here \
           pasanabeysekara/dinefy-data-service
    ```
    Replace `<your_mysql_host_or_ip>` with the actual host/IP of your MySQL server accessible from Docker. If running MySQL in another Docker container on the same network, you can use the service name.
    Ensure all necessary environment variables (like database credentials, JWT secret) are passed to the container.

## CI/CD

A CI/CD pipeline is configured using GitHub Actions in `.github/workflows/ci-cd.yml`. This pipeline likely handles:
*   Building the application on pushes/pull requests.
*   Running tests.
*   (Potentially) Building and pushing Docker images to a registry.
*   (Potentially) Deploying the application to a staging/production environment.

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1.  **Fork the repository.**
2.  **Create a new branch** for your feature or bug fix:
    ```bash
    git checkout -b feature/your-feature-name
    ```
3.  **Make your changes** and commit them with descriptive messages.
4.  **Ensure tests pass:**
    ```bash
    ./mvnw test
    ```
5.  **Push your changes** to your forked repository:
    ```bash
    git push origin feature/your-feature-name
    ```
6.  **Open a Pull Request** to the `main` (or `develop`) branch of the original repository.

Please ensure your code adheres to the existing coding style and includes relevant tests.


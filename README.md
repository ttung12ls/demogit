# Spring Boot WebFlux Order Management System

This project is a reactive Spring Boot application using WebFlux, R2DBC MySQL, and Swagger for API documentation.

## Prerequisites

- Docker and Docker Compose
- Java 17
- Maven

## Getting Started

### 1. Start MySQL Database

```bash
docker-compose up -d
```

This command will:
- Start MySQL 8.0 container
- Create the 'orders' database
- Set up the necessary user accounts
- Expose MySQL on port 3306

### 2. Run the Application

```bash
./mvnw spring-boot:run
```

### 3. Access the Application

- Swagger UI: http://localhost:8080/swagger-ui.html
- API Documentation: http://localhost:8080/v3/api-docs

## Docker Commands

- Start containers: `docker-compose up -d`
- Stop containers: `docker-compose down`
- View logs: `docker-compose logs -f`
- Restart containers: `docker-compose restart`

## Database Configuration

MySQL is configured with:
- Database: orders
- Username: root
- Password: meomeo123
- Port: 3306
- SSL Mode: DISABLED

## API Endpoints

- GET /api/order - Get all orders
- GET /api/order/{orderId} - Get order by ID
- POST /api/order - Create new order
- PUT /api/order/{orderId} - Update order
- DELETE /api/order/{orderId} - Delete order
- GET /api/order/customer/{customer} - Find order by customer name
- GET /api/order/checkDuplicate/{customer} - Check if customer order exists

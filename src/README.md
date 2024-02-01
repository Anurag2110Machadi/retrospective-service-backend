# Retrospective Service

## Overview
This project is a simple retrospective service that allows users to create retrospectives, add feedback items, and perform basic CRUD operations. It is implemented using Spring Boot and Java.

## Tools and Libraries Used
- **Spring Boot:** Used for building and managing the application.
- **Maven:** Used as the build and dependency management tool.
- **JUnit and Mockito:** Used for unit testing.
- **Java 17:** Programming language for the project.

## How to Run the Application

### Prerequisites
- Java 17 installed.
- Maven installed.

### Run the application
- mvn spring-boot:run
- The application will start on http://localhost:8080.

### API Endpoints
- Create Retrospective:
- Endpoint: POST /retrospectives
- Body: JSON representing the retrospective details.

- Add Feedback Item:
- Endpoint: POST /retrospectives/{id}/feedback
- Body: JSON representing the feedback item details.

- Update Feedback Item:
- Endpoint: PUT /retrospectives/{id}/feedback/{itemId}
- Body: JSON representing the updated feedback item details.

- Get All Retrospectives:
- Endpoint: GET /retrospectives
- Query Parameters: page (default: 1), pageSize (default: 10).

- Search Retrospectives by Date:
- Endpoint: GET /retrospectives/search
- Query Parameters: date, page (default: 1), pageSize (default: 10).
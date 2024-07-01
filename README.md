# Device Management System

## Overview

The Device Management System is a CRUD (Create, Read, Update, Delete) application that manages device information. It
provides RESTful API endpoints to perform operations on devices, such as adding new devices, retrieving existing
devices, updating device details, and deleting devices.

## Features

- **Create Device**: Add a new device to the system.
- **Read Device**: Retrieve device details by ID or get a list of all devices.
- **Update Device**: Modify existing device details.
- **Delete Device**: Remove a device from the system.
- **Filter Devices**: Retrieve devices based on their brand.

## Tech Stack

- **Java 21**
- **Spring Boot 3.3.1**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database** (for testing and development)
- **Lombok**
- **Mockito & JUnit 5** (for testing)
- **Swagger UI** (for API documentation and testing)

## Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/ramimohsen/device-management.git
    cd device-management
    ```

2. **Build the project**:
    ```sh
    ./mvnw clean install
    ```

3. **Run the application**:
    ```sh
    ./mvnw spring-boot:run
    ```

## Usage

Once the application is running, you can access the Swagger UI to interact with the API:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

### Device Controller

| Method | Endpoint                     | Description               |
|--------|------------------------------|---------------------------|
| GET    | /api/v1/device/{id}          | Retrieve device by ID     |
| GET    | /api/v1/device               | Retrieve all devices      |
| POST   | /api/v1/device               | Create a new device       |
| PUT    | /api/v1/device               | Update an existing device |
| DELETE | /api/v1/device/{id}          | Delete a device by ID     |
| GET    | /api/v1/device/brand/{brand} | Retrieve devices by brand |

## Example JSON Requests

## Running Tests

To run the tests, execute the following command:

```sh
./mvnw test
```

### Create Device

POST /api/v1/device
Content-Type: application/json

```json
{
  "name": "iPhone 13",
  "brand": "APPLE"
}
```

PUT /api/v1/device
Content-Type: application/json

```json
{
  "id": 1,
  "name": "iPhone 13 Pro",
  "brand": "APPLE"
}
```
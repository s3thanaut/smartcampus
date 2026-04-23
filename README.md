# Smart Campus Sensor & Room Management API

## Project Overview
This project is a RESTful web service built for the "Smart Campus" initiative to manage university rooms and the various sensors (CO2, temperature, occupancy) located within them. As the Lead Backend Architect, I developed this system to provide a scalable and robust way for facilities managers to interact with campus data.

## Technology Stack
- **Language:** Java 8
- **Framework:** JAX-RS (Jersey Implementation)
- **Server:** Apache Tomcat 8
- **Build Tool:** Maven
- **Data Storage:** In-memory Collections (ArrayList)

## Core Technical Aspects

### 1. API Architecture
The API follows a versioned entry point at `/api/v1`. It features a "Discovery" endpoint at the root that provides metadata and navigation links to other resources, adhering to HATEOAS principles.

### 2. Resource Modeling
- **Rooms:** Tracks capacity, name, and assigned sensors.
- **Sensors:** Tracks type (CO2, Occupancy, etc.), status (ACTIVE, MAINTENANCE, OFFLINE), and current measurement values.
- **Readings:** A nested sub-resource that maintains a historical log of data captured by each specific sensor.

### 3. Implementation Details
- **Sub-Resource Locators:** Used to handle deep nesting (e.g., `/sensors/{id}/readings`). This keeps the code modular by delegating reading-specific logic to a dedicated resource class.
- **Referential Integrity:** When registering a new sensor, the system manually verifies that the specified `roomId` exists.
- **Data Consistency:** Every time a new reading is POSTed, the system automatically updates the `currentValue` on the parent Sensor object.

### 4. Advanced Error Handling (Leak-Proof API)
The system uses custom `ExceptionMappers` to intercept Java exceptions and return professional JSON responses instead of stack traces:
- **409 Conflict:** If you try to delete a room that still has sensors.
- **422 Unprocessable Entity:** If you try to link a sensor to a room that doesn't exist.
- **403 Forbidden:** If you try to add a reading to a sensor that is in 'MAINTENANCE' or 'OFFLINE' mode.
- **500 Internal Server Error:** A global "catch-all" to hide internal server details from users.

### 5. Observability
A custom `LoggingFilter` is implemented to log every incoming HTTP request (Method and URI) and outgoing response (Status Code) to the console for monitoring.

## How to Build and Run
### Using mvn CLI
1. Clone the repository.
2. Ensure you have Maven and Tomcat 8 installed.
3. Run `mvn clean package`.
4. Deploy the generated `smartcampus.war` to your Tomcat `webapps` folder.
5. Access the API at `http://localhost:8080/smartcampus/api/v1`.

### Using NetBeans
1. Import the Maven project
2. Download and add Tomcat 8 as the Server
3. Clean and Build the Project
4. Run the Tomcat Server and the Project
5. Access the API at `http://localhost:8080/smartcampus/api/v1`.

## API End points
| Request Method | API Endpoint Example | Description |
|---|---|---|
| `GET` | `/smartcampus/api/v1` | Checks the availability and base connectivity of the Smart Campus API. |
| `GET` | `/smartcampus/api/v1/rooms` | Retrieves a list of all rooms registered in the Smart Campus system. |
| `GET` | `/smartcampus/api/v1/rooms/AUDI` | Fetches the details of a specific room identified by its ID. |
| `POST` | `/smartcampus/api/v1/rooms` | Adds a new room to the Smart Campus system with the provided details. |
| `DELETE` | `/smartcampus/api/v1/rooms/LAB-2` | Deletes a specific room (LAB-2) from the Smart Campus system. |
| `GET` | `/smartcampus/api/v1/sensors` | Retrieves a list of all sensors registered in the Smart Campus system. |
| `GET` | `/smartcampus/api/v1/sensors?type=co2` | Retrieves all sensors filtered by a specific type (e.g., CO2). |
| `POST` | `/smartcampus/api/v1/sensors` | Registers a new sensor in the Smart Campus system. |
| `GET` | `/smartcampus/api/v1/sensors/SEN-0002/readings` | Retrieves all recorded readings for a specific sensor. |
| `POST` | `/smartcampus/api/v1/sensors/SEN-0001/readings` | Logs a new reading entry for a specific active sensor. |


## Sample CURL Commands
- **Discovery:** `curl -X GET http://localhost:8080/smartcampus/api/v1`
- **Get All Rooms:** `curl -X GET http://localhost:8080/smartcampus/api/v1/rooms`
- **Add a Sensor:** `curl -X POST -H "Content-Type: application/json" -d '{"type":"<Type>", "roomId":"<Room-ID>"}' http://localhost:8080/smartcampus/api/v1/sensors`
- **Add a Reading:** `curl -X POST -H "Content-Type: application/json" -d '{"value":<value>}' http://localhost:8080/smartcampus/api/v1/sensors/<Sensor-ID>/readings`
- **Delete a Room:** `curl -X DELETE http://localhost:8080/smartcampus/api/v1/rooms/<Room-ID>`
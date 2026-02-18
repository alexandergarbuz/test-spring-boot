# Project Structure

This document describes the folder layout and purpose of each package in the `test-spring-boot` Spring Boot web service.

---

## Overview

```
test-spring-boot/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/garbuz/web/
│   │   │   ├── TestSpringBootApplication.java
│   │   │   ├── ServletInitializer.java
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── schema.sql
│   │       ├── data.sql
│   │       └── static/
│   └── test/
│       └── java/com/garbuz/web/
│           └── repository/
└── target/
```

---

## Root Files

| File | Purpose |
|---|---|
| `pom.xml` | Maven build descriptor. Declares dependencies (Spring Boot, JPA, Swagger/OpenAPI, H2, etc.), Java version (21), and packaging type (WAR). |
| `README.md` | General project notes. |

---

## `src/main/java/com/garbuz/web/` — Application Root

| File | Purpose |
|---|---|
| `TestSpringBootApplication.java` | Entry point. Contains `main()` and is annotated with `@SpringBootApplication`, which triggers component scanning, auto-configuration, and Spring context startup. |
| `ServletInitializer.java` | Extends `SpringBootServletInitializer` to support WAR deployment to an external servlet container (e.g., Tomcat, Jetty). When the app is deployed as a WAR, the container discovers this class and uses it to bootstrap the Spring application context instead of the embedded server path. |

---

## `controller/` — Web / REST Layer

**Package:** `com.garbuz.web.controller`

Handles incoming HTTP requests and returns responses. Controllers are annotated with `@RestController` and define endpoint mappings via `@RequestMapping`, `@GetMapping`, etc.

| File | Purpose |
|---|---|
| `HelloWorldController.java` | Exposes REST endpoints under `/hello-world`. Accepts query parameters, delegates business logic to `HelloService`, and returns `ResponseEntity` objects. Also annotated with OpenAPI/Swagger `@Tag` and `@Operation` annotations for API documentation. |

**Responsibilities:**
- Map HTTP verbs and URL paths to handler methods
- Validate/parse request inputs
- Call the service layer
- Build and return HTTP responses

---

## `dto/` — Data Transfer Objects

**Package:** `com.garbuz.web.dto`

Plain Java objects used to shape the data sent to or received from the API. DTOs decouple the internal domain model from the external API contract, preventing the direct exposure of JPA entities over the wire.

| File | Purpose |
|---|---|
| `HelloDto.java` | Carries `firstName`, `lastName`, and `message` fields as the response payload returned by the controller. Implements `equals`/`hashCode` for comparison safety. |

**Responsibilities:**
- Define the shape of API request/response bodies
- Prevent leaking internal entity details (e.g., database IDs, JPA annotations) to API consumers

---

## `model/` — Domain / Entity Layer

**Package:** `com.garbuz.web.model`

JPA entity classes that map directly to database tables. Annotated with `@Entity`, `@Table`, `@Id`, `@Column`, etc.

| File | Purpose |
|---|---|
| `Message.java` | Represents a row in the `Message` database table. Fields: `id` (auto-generated PK), `firstName`, `lastName`, `message`. |

**Responsibilities:**
- Define the persistent data model
- Map Java objects to relational database tables via JPA annotations
- Serve as the canonical in-memory representation of persisted data

---

## `repository/` — Data Access Layer

**Package:** `com.garbuz.web.repository`

Spring Data JPA repository interfaces that provide database access. By extending `JpaRepository`, Spring automatically generates the standard CRUD operations at runtime. Custom query methods can be declared as method signatures following Spring Data's naming conventions.

| File | Purpose |
|---|---|
| `MessageDao.java` | Repository interface for `Message` entities. Extends `JpaRepository<Message, Long>` for full CRUD support. Declares `findByLastNameAndFirstName()` as a custom derived query. Annotated with `@Repository` and `@Transactional`. |

**Responsibilities:**
- Abstract all database interaction away from the service layer
- Provide CRUD operations via Spring Data JPA
- Define custom queries using method naming conventions or `@Query` annotations

---

## `service/` — Business Logic Layer

**Package:** `com.garbuz.web.service`

Contains the application's business logic. Services are annotated with `@Service` and sit between the controller layer (web concerns) and the repository layer (data concerns). This is where orchestration, validation, and transformation of data takes place.

| File | Purpose |
|---|---|
| `HelloService.java` | Provides `lookUpByFirstAndLastName()` and `findAllMessages()` business operations. Injects `MessageDao` to query the database and includes structured logging via SLF4J. |

**Responsibilities:**
- Implement business rules and application workflows
- Coordinate calls to one or more repositories
- Transform domain models as needed before returning data to the controller

---

## `src/main/resources/` — Configuration & Data

| File / Folder | Purpose |
|---|---|
| `application.properties` | Spring Boot configuration (datasource, JPA settings, server port, logging levels, etc.). |
| `schema.sql` | DDL script executed at startup to create the database schema (tables, constraints). Used with an embedded database such as H2. |
| `data.sql` | DML script executed at startup to seed the database with initial data. |
| `static/` | Static web assets served directly by Spring Boot's embedded web server without any controller involvement. |

### `static/` — Front-End Assets

| Folder | Purpose |
|---|---|
| `static/index.html` | Main HTML page served at the root URL. |
| `static/css/` | Custom application stylesheets (`default.css`, `custom-functions.css`). |
| `static/js/` | Client-side JavaScript (jQuery 3.7.1). |
| `static/bootstrap-5.3.3-dist/` | Bootstrap 5.3.3 CSS and JS distribution files for responsive UI layout and components. |
| `static/font-awesome-4.7.0/` | Font Awesome 4.7.0 icon library (CSS + web fonts). |
| `static/img/` | Application image assets. |

---

## `src/test/java/com/garbuz/web/` — Tests

| Package / File | Purpose |
|---|---|
| `TestSpringBootApplicationTests.java` | Root integration test that verifies the Spring application context loads without errors. |
| `repository/MessageDaoTest.java` | Unit/integration tests for the `MessageDao` repository, verifying custom query methods against an embedded database. |

---

## `target/` — Build Output

Generated by Maven (`mvn package`). Contains compiled `.class` files, copied resources, and the packaged WAR artifact. This directory is not committed to source control.

---

## Layer Dependency Flow

```
HTTP Request
     │
     ▼
 controller/        ← handles HTTP, maps routes, returns responses
     │
     ▼
  service/          ← business logic, orchestration
     │
     ▼
 repository/        ← data access (Spring Data JPA)
     │
     ▼
   model/           ← JPA entities (maps to DB tables)
     │
     ▼
  Database
```

DTOs (`dto/`) are used at the **controller ↔ service boundary** to shape data going out to API consumers without exposing raw entity objects.

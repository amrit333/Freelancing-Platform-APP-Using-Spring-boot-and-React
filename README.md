# Freelancing Platform API

This is a complete production-ready Freelancing Platform backend built with **Java 17**, **Spring Boot**, **Spring Security (JWT)**, **Spring Data JPA**, **MySQL**, **MapStruct**, and **Swagger**.

## 🧱 Architecture & Modules

* **Auth Module:** JWT authentication, Role-based access control (CLIENT, FREELANCER).
* **Project Module:** Clients can post, edit, delete, and view their projects.
* **Bidding Module:** Freelancers can bid on open projects. Clients can view bids.
* **Contract Module:** Clients can accept bids, which updates the project status and creates a contract.
* **Messaging Module:** Users can communicate with each other securely.
* **Review System:** Clients and freelancers can review each other upon project completion.

## 🚀 Setup & Run Instructions

### Prerequisites
1. **Java 17+**
2. **Maven**
3. **MySQL Server** (Running on `localhost:3306`)

### 1. Database Configuration
By default, the application is configured to create the database if it doesn't exist (`freelance_db`). 
Make sure your MySQL credentials in `src/main/resources/application.yml` match your local setup:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/freelance_db?createDatabaseIfNotExist=true
    username: root
    password: <your-password>
```

### 2. Build the Application
Open a terminal in the root directory and run:

```bash
mvn clean install
```
*(If you don't have Maven installed globally, you can download it from maven.apache.org or use your IDE's built-in Maven)*

### 3. Run the Application
You can run it using Maven:

```bash
mvn spring-boot:run
```

Or run the `PlatformApplication` class directly from your IDE (IntelliJ / Eclipse).

---

## 📚 API Documentation

Once the application is running, navigate to the **Swagger UI** for complete interactive API documentation:
👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

---

## 🧪 Sample API Requests (Postman)

### 1. Register Client
**POST** `http://localhost:8080/api/auth/register`
```json
{
  "name": "John Client",
  "email": "client@example.com",
  "password": "password123",
  "role": "CLIENT"
}
```

### 2. Register Freelancer
**POST** `http://localhost:8080/api/auth/register`
```json
{
  "name": "Alice Freelancer",
  "email": "freelancer@example.com",
  "password": "password123",
  "role": "FREELANCER"
}
```

### 3. Login
**POST** `http://localhost:8080/api/auth/login`
```json
{
  "email": "client@example.com",
  "password": "password123"
}
```
*Take the `accessToken` from the response and use it as `Bearer <token>` in the Authorization header for subsequent requests.*

### 4. Create Project (As CLIENT)
**POST** `http://localhost:8080/api/projects`
```json
{
  "title": "Build a React Website",
  "description": "Looking for a React expert to build a corporate website.",
  "budget": 1500.00,
  "deadline": "2026-12-31"
}
```

### 5. Submit Bid (As FREELANCER)
**POST** `http://localhost:8080/api/bids`
```json
{
  "projectId": 1,
  "proposal": "I am a senior React developer. I can deliver this perfectly.",
  "amount": 1200.00
}
```

### 6. Accept Bid (As CLIENT)
**POST** `http://localhost:8080/api/contracts/accept`
```json
{
  "bidId": 1
}
```

### 7. Send Message
**POST** `http://localhost:8080/api/messages`
```json
{
  "receiverId": 2,
  "content": "Hello, I accepted your bid. When can we start?"
}
```

### 8. Add Review
**POST** `http://localhost:8080/api/reviews`
```json
{
  "revieweeId": 2,
  "projectId": 1,
  "rating": 5,
  "comment": "Excellent work!"
}
```

## 🔒 Security Summary
* Handled via JWT token filter (`JwtAuthenticationFilter`).
* Extracted User details securely on protected endpoints using `@PreAuthorize` and `Authentication` principal.
* Passwords are encrypted using `BCryptPasswordEncoder`.

## 📌 Output Quality
* **Clean Code:** Handled Exceptions globally, utilized MapStruct to strictly separate Entities from DTOs.
* **Scalable:** Followed layered architecture (`Controller` -> `Service` -> `Repository`).
* **Validation:** All inputs are validated via `spring-boot-starter-validation` annotations (e.g. `@NotBlank`, `@DecimalMin`, `@Valid`).

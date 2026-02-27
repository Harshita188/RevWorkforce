🌟 RevWorkforce – HRM System (Backend)
✨ Spring Boot · JWT Security · MySQL · REST APIs
<div align="center">
🚀 A Modern Backend for a Complete Human Resource Management System

Built with Spring Boot (Backend) + Angular (Frontend)
Secure, scalable, and enterprise-ready HR management application.

</div>
📌 Project Summary

RevWorkforce is a monolithic HR Management System supporting:

👤 Employees

👨‍💼 Managers

🛠 Admins

Key features include:

✔ Authentication (JWT)
✔ Leave Management
✔ Performance Reviews
✔ Department & Designation Management
✔ Team Structure
✔ Admin Dashboards
✔ Role-Based Access Control

🧭 Project Ports
Layer	URL
🔵 Frontend (Angular)	http://localhost:4200

🟢 Backend (Spring Boot)	http://localhost:8080

🏗️ Backend Architecture

RevWorkforce Backend
│
├── Controller Layer      → REST Endpoints
├── Service Layer         → Business Logic
├── Repository Layer      → JPA Repositories
├── Entity Layer          → Database Models
└── Security Layer        → Spring Security + JWT

⚙️ Tech Stack
| Category          | Technology            |
| ----------------- | --------------------- |
| Backend Framework | Spring Boot           |
| Language          | Java 17+              |
| Database          | MySQL                 |
| ORM               | Spring Data JPA       |
| Security          | JWT + Spring Security |
| Build Tool        | Maven                 |
| Architecture      | Monolithic            |
| Frontend          | Angular               |

📁 Folder Structure

src/main/java/com/revworkforce/
│
├── controller/
├── service/
├── repository/
├── security/
│   ├── JwtUtil.java
│   ├── JwtAuthenticationFilter.java
│   ├── SecurityConfig.java
│   └── CustomUserDetails.java
│
├── entity/
│   ├── User.java
│   ├── Role.java
│   ├── Leave.java
│   ├── LeaveType.java
│   ├── LeaveBalance.java
│   ├── Department.java
│   ├── Designation.java
│   ├── Holiday.java
│   └── Performance.java
│
└── RevWorkforceApplication.java

🔑 API Endpoints (Summary)
| Method | Endpoint      | Description          |
| ------ | ------------- | -------------------- |
| POST   | `/auth/login` | Login & generate JWT |

👥 USER MODULE
| Method | Endpoint             | Access                 |
| ------ | -------------------- | ---------------------- |
| GET    | `/api/users`         | Admin                  |
| GET    | `/api/users/me`      | Employee               |
| GET    | `/api/users/my-team` | Manager                |
| POST   | `/api/users`         | Admin                  |
| PUT    | `/api/users/{id}`    | Admin/Manager/Employee |
| DELETE | `/api/users/{id}`    | Admin                  |

📝 LEAVES MODULE
| Method | Endpoint                   | Access   |
| ------ | -------------------------- | -------- |
| POST   | `/api/leaves`              | Employee |
| PUT    | `/api/leaves/{id}/approve` | Manager  |
| PUT    | `/api/leaves/{id}/reject`  | Manager  |
| PUT    | `/api/leaves/{id}/cancel`  | Employee |
| GET    | `/api/leaves/my`           | Employee |
| GET    | `/api/leaves/pending`      | Manager  |
| GET    | `/api/leaves/all`          | Admin    |

🛢️ Database Tables
users
roles
user_roles
departments
designations
leaves
leave_types
leave_balances
holidays
performance

🚀 How to Run Backend
✅ 1. Clone Repo
git clone https://github.com/your-repo/revworkforce-backend.git
✅ 2. Configure Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/revworkforce
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
✅ 3. Install Dependencies
mvn clean install
✅ 4. Start Server
mvn spring-boot:run

Backend runs at: http://localhost:8080

🔐 JWT Authentication Flow
Client → /auth/login
Backend → Validates credentials
Backend → Returns JWT Token
Client → Sends token in headers (Authorization: Bearer <token>)
Backend → Verifies token on each request
Access Granted ✔

🧪 Testing

Postman

Thunder Client

Swagger (if enabled)

Angular HTTP services

🏁 Conclusion

RevWorkforce Backend offers:

⚡ Fast, secure API services

🔐 JWT-based role management

📊 Scalable HRMS architecture

💼 Complete Admin + Manager + Employee workflows

<div align="center">
💡 Want a stylish Frontend README too?

I can prepare it exactly like this — clean & professional.

</div>

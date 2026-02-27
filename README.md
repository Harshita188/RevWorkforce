⭐ RevWorkforce – HRM System Backend
🚀 Spring Boot · JWT · MySQL · REST APIs
🔗 Overview

RevWorkforce is a full-stack Human Resource Management System with:

🔐 Secure Authentication (JWT)

👥 Employee, Manager & Admin Roles

📝 Leave Management

⭐ Performance Review

🗂 Department & Designation Management

📅 Holiday Calendar

🧠 Team Structure & Reporting Hierarchy

This repository contains the backend (Spring Boot).
Frontend (Angular) runs on: http://localhost:4200

Backend runs on: http://localhost:8080

🧱 Architecture
Controller Layer → REST Endpoints
Service Layer    → Business Logic
Repository Layer → JPA Repositories
Entity Layer     → ORM Models
Security Layer   → Spring Security + JWT
⚙️ Tech Stack
Layer	Technology
Backend Framework	Spring Boot
Language	Java 17+
Database	MySQL
ORM	Spring Data JPA
Security	Spring Security + JWT
Build Tool	Maven
Architecture	Monolithic
Frontend	Angular (PORT: 4200)
🗂 Project Structure
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
🌐 Base URLs
Frontend (Angular): http://localhost:4200
Backend (Spring Boot): http://localhost:8080
🔑 API Endpoints
Auth
Method	Endpoint	Description
POST	/auth/login	Login & Get JWT Token
Users
Method	Endpoint	Role
GET	/api/users	Admin
GET	/api/users/me	Employee
GET	/api/users/my-team	Manager
POST	/api/users	Admin
PUT	/api/users/{id}	Admin/Manager/Employee
DELETE	/api/users/{id}	Admin
Leaves
Method	Endpoint	Role
POST	/api/leaves	Employee
PUT	/api/leaves/{id}/approve	Manager
PUT	/api/leaves/{id}/reject	Manager
PUT	/api/leaves/{id}/cancel	Employee
GET	/api/leaves/my	Employee
GET	/api/leaves/pending	Manager
GET	/api/leaves/all	Admin
🛢 Database Tables

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

🚀 How to Run the Backend
1. Clone the Repository
git clone https://github.com/your-repo/revworkforce-backend.git
2. Configure MySQL

Edit application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/revworkforce
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
3. Build Project
mvn clean install
4. Run Server
mvn spring-boot:run

Backend starts at:
👉 http://localhost:8080

🔐 JWT Flow

User logs in → /auth/login

Server generates JWT token

Frontend stores token

All protected APIs require:

Authorization: Bearer <token>

Spring Security validates every request

🧪 Testing

Postman

Thunder Client

Swagger (if enabled)

Angular Services (HttpClient)

🏁 Conclusion

This backend powers all major HRM features for:

Admin

Manager

Employee

Works perfectly with the Angular frontend on port 4200.

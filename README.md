# 📄 Resume Builder Application

A full-stack Resume Builder Web Application developed using Spring Boot, Thymeleaf, and H2 In-Memory Database.  
This application allows users to create, edit, and manage professional resumes through a simple web interface.

---

## 📌 Project Overview

The Resume Builder Application helps users generate resumes by entering personal, educational, and professional details.  
It uses Spring Boot for backend logic, Thymeleaf for UI rendering, and H2 database for lightweight data storage.

---

## ✨ Features

- User Registration & Login  
- Create and Edit Resume  
- Add Education, Skills, and Experience  
- Resume Preview  
- Download Resume (PDF / Print)  
- Form Validation  
- Exception Handling  
- H2 In-Memory Database Integration  

---

## 🛠️ Technologies Used

- Java  
- Spring Boot  
- Spring MVC  
- Spring Data JPA  
- Thymeleaf  
- Hibernate  
- H2 Database  
- Maven  
- HTML, CSS, Bootstrap  

---

## 📂 Project Structure

src/main/java
 ├── controller
 ├── service
 ├── repository
 ├── entity
 └── exception

src/main/resources
 ├── templates
 ├── static
 └── application.properties

---

## ⚙️ Database Configuration (H2)

Update database settings in:

src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:h2:mem:resumedb  
spring.datasource.driverClassName=org.h2.Driver  
spring.datasource.username=sa  
spring.datasource.password=  

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect  
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  

spring.h2.console.enabled=true  
spring.h2.console.path=/h2-console  

---

## ▶️ How to Run the Project

Step 1: Clone Repository

git clone <your-repository-url>

Step 2: Open in IDE

Open in IntelliJ / Eclipse / STS

Step 3: Run Application

Run as Spring Boot App  
or

mvn spring-boot:run

---

## 🌐 Application URL

Main Application:

http://localhost:8080

H2 Database Console:

http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:resumedb  
Username: sa  
Password: (empty)

---

## 🔐 Security (If Implemented)

- Password Encryption  
- Session Management  
- Role-based Access  

---

## 🧪 Testing

- Browser UI Testing  
- Form Validation Testing  
- H2 Console Verification  

---

## 🚀 Future Enhancements

- PDF Resume Download  
- Multiple Resume Templates  
- Profile Picture Upload  
- Cloud Storage Integration  
- User Dashboard  

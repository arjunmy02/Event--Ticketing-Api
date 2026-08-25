# Event Ticketing System

A full-stack event ticketing application built with **Java, Spring Boot, PostgreSQL, HTML, CSS, and JavaScript**. The system provides REST APIs for managing events and booking tickets while storing booking and event data in PostgreSQL.

## 🚀 Features

* View available events
* Create and manage events
* Book tickets for events
* Track available seats
* Calculate ticket booking price
* RESTful API architecture
* PostgreSQL database integration
* Responsive frontend

## 🛠️ Tech Stack

### Frontend

* HTML
* CSS
* JavaScript

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Maven
* REST APIs

### Database

* PostgreSQL

## 📁 Project Structure

```text
Event-Ticketing-App/
│
├── Frontend/
│   ├── index.html
│   ├── style.css
│   └── script.js
│
└── Backend/
    ├── src/
    │   └── main/
    │       └── java/
    ├── pom.xml
    └── application.yml
```

## 🔄 Application Flow

```text
User
  ↓
Frontend
  ↓
REST API
  ↓
Spring Boot Controller
  ↓
Service Layer
  ↓
JPA Repository
  ↓
PostgreSQL
```

## 📌 API Endpoints

### Events

| Method | Endpoint       | Description     |
| ------ | -------------- | --------------- |
| GET    | `/events`      | Get all events  |
| GET    | `/events/{id}` | Get event by ID |
| POST   | `/events`      | Create an event |

### Tickets

| Method | Endpoint        | Description  |
| ------ | --------------- | ------------ |
| POST   | `/tickets/book` | Book tickets |

## 🗄️ Database

The application uses **PostgreSQL** to store event and ticket information.

Main entities:

```text
Event
 ├── id
 ├── eventName
 ├── location
 ├── availableSeats
 └── ticketPrice

Ticket
 ├── ticketId
 ├── eventId
 ├── customerName
 ├── seatsBooked
 ├── bookedAt
 └── totalPrice
```

## 🔮 Future Improvements

* [ ] User registration and login
* [ ] JWT authentication
* [ ] USER / ADMIN roles
* [ ] Event search and filtering
* [ ] Ticket cancellation
* [ ] Booking history
* [ ] Admin dashboard
* [ ] Online payment integration
* [ ] Email notifications
* [ ] Automated testing
* [ ] Docker support
* [ ] GitHub Actions CI/CD

## ▶️ Getting Started

### 1. Clone the repository

```bash
git clone <your-repository-url>
```

### 2. Configure PostgreSQL

Create a PostgreSQL database and update the database credentials in the backend configuration.

### 3. Run the backend

```bash
cd Backend
mvn spring-boot:run
```

### 4. Run the frontend

Open the frontend files in a browser or run them using a local development server.

## 👨‍💻 Author

**Arjun Yadav**

B.Tech Computer Science & Information Technology

Interested in **Java, Spring Boot, Backend Development, and DSA**.

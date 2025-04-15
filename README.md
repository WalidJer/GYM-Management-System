# Keyin Gym Management System

Welcome to the Keyin Gym Management System — a console-based Java application for managing gym memberships, users, and workout classes.

## Features

- User Registration & Login (Admin, Trainer, Member)
- Secure password hashing using BCrypt
- Role-based Menus and Access Control
- Membership Management (purchase, view, revenue/expenses tracking)
- Workout Class Management (create, update, delete, view)
- PostgreSQL database integration

## Technologies Used

- Java 21
- Maven
- PostgreSQL
- BCrypt for password hashing

## How to Run

1. **Install Requirements**
   - Java 21+
   - PostgreSQL
   - Maven

2. **Set up PostgreSQL**
   - Create a database named `DB1`
   - Run the provided SQL script to create tables (`users`, `memberships`, `workout_classes`)

3. **Configure Database Connection**
   - Edit `DBConnection.java` with your database URL, username, and password

4. **Run the App**
   ```bash
   mvn clean compile exec:java
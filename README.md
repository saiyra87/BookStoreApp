# COE528 Bookstore Application

This is a JavaFX-based Bookstore Application developed as part of the
Winter 2025 COE528 course project. The application is GUI-driven and
demonstrates the use of object-oriented principles, JavaFX components,
and the State Design Pattern.

## Project Objectives

- Analyze, design, and implement a bookstore application.
- Design with UML diagrams (Use Case and Class Diagrams).
- Apply the State Design Pattern in software design.
- Implement a fully functional system with a single-window JavaFX GUI.
- Develop and submit the project as a group.

## Features

### General
- Single-window GUI (no multiple windows).
- Auto-save data to `books.txt` and `customers.txt` on exit.
- Auto-load data from files on startup.

### User Roles
- **Owner (admin/admin)**:
  - View, add, and delete books.
  - View, add, and delete customers.
  - Logout functionality.

- **Customer (registered by owner)**:
  - View books and purchase them.
  - Earn and redeem points.
  - Points determine status (Silver or Gold).
  - Logout functionality.

### Points System
- 10 points earned per 1 CAD spent.
- 100 points redeemed = 1 CAD discount.
- Status:
  - Silver: < 1000 points
  - Gold: ≥ 1000 points

## Design & Implementation

- **UML Diagrams**: Created with Visual Paradigm.
  - `useCaseDiagram.pdf`
  - `classDiagram.pdf` (State Pattern classes indicated)
- **State Design Pattern**: Used to handle customer status transitions
(Silver ↔ Gold).
- **JavaFX**: GUI built with JavaFX using `TableView<S>` and standard
components.
- **FXML**: May be used, but no dependency on Scene Builder for compilation.

## Tools and Requirements

- JavaFX (Java 8+)
- NetBeans IDE (no Maven)
- Visual Paradigm for UML diagrams
- No external dependencies

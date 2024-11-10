# N3 Donation Management Application

## Short Description

The N3 Donation Management Application is a comprehensive web application designed to facilitate the registration, management, and reporting of donations. It provides a user-friendly interface for managing donations, allowing users to register new donations, view a list of existing donations, generate reports, and export those reports in CSV and PDF formats. The application is built using modern frontend and backend technologies, ensuring a smooth and efficient user experience.

## Overview

### Architecture and Technologies

The N3 Donation Management Application is built using a combination of frontend and backend technologies:

- **Frontend**:
  - **JavaScript**: For client-side scripting and handling user interactions.
  - **HTML**: For structuring the user interface.
  - **CSS**: For styling the application.
  - **Bootstrap 5**: For a responsive and user-friendly layout. Bootstrap is loaded from the CDN.
  - **Fetch API**: For making HTTP requests to the server.

- **Backend**:
  - **Spring Boot**: For creating the RESTful API and managing the application's server-side logic.
  - **Java**: For writing the backend code.
  - **JPA (Java Persistence API)**: For database interactions.
  - **PostgreSQL**: For storing and retrieving donation data.
  - **Lombok**: To reduce boilerplate code by automatically generating getters, setters, and other utility methods.
  - **Validation Annotations**: From the `jakarta.validation.constraints` package to ensure data integrity.
  - **CSV and PDF Generation**: Using libraries such as `Apache Commons CSV` and `iTextPDF` to generate CSV and PDF files.

### Project Structure

- **Frontend**:
  - `index.html`: The main entry point of the application.
  - `donation-form.js`: Handles the form submission for registering new donations.
  - `donation-list.js`: Handles the display and pagination of the donation list.
  - `donation-reports.js`: Handles the form submission for generating and exporting donation reports.
  - `donation-view.js`: Handles the display of detailed donation information.
  - `donation-edit.js`: Handles the form submission for editing donations.

- **Backend**:
  - `src/main/java/com/gerenciamento/backend/DonativosApplication.java`: The entry point of the Spring Boot application.
  - `src/main/java/com/gerenciamento/backend/controller/DonationController.java`: Handles HTTP requests related to donations.
  - `src/main/java/com/gerenciamento/backend/controller/ReportController.java`: Handles HTTP requests related to generating and exporting donation reports.
  - `src/main/java/com/gerenciamento/backend/service/DonationService.java`: Provides business logic for managing donations.
  - `src/main/java/com/gerenciamento/backend/service/ReportService.java`: Provides business logic for generating and exporting donation reports.
  - `src/main/java/com/gerenciamento/backend/repository/DonationRepository.java`: A Spring Data JPA repository interface for performing CRUD operations on the `Donation` entity.
  - `src/main/java/com/gerenciamento/backend/model/Donation.java`: The entity class representing a donation.
  - `src/main/java/com/gerenciamento/backend/model/ReportFilter.java`: A class used to represent and validate filters for generating reports.

## Features

### User Interface
- **Register Donation**:
  - Form fields for name, type, quantity, donor, date of receipt, expiry date, and validity period.
  - Form validation to ensure data integrity.
  - Form submission to register a new donation.
  - Form reset after successful submission.

- **Donation List**:
  - Paginated list of donations displayed in an HTML table.
  - Table includes columns for name, type, quantity, donor, date of receipt, expiry date, and validity period.
  - Pagination controls for navigating through multiple pages of donations.

- **Donation Reports**:
  - Form filters for date range, type of donation, and donor.
  - Generate and display reports based on the provided filters.
  - Export reports as CSV and PDF files.

- **Detailed Donation View**:
  - Display detailed information about a specific donation.
  - Links to edit or delete the donation.

- **Edit Donation**:
  - Form with current donation data for editing.
  - Form validation to ensure data integrity.
  - Update the donation after form submission.

- **Delete Donation**:
  - Confirmation message before deleting a donation.
  - Delete the donation from the database after confirmation.

## Getting Started

### Requirements

To run the N3 Donation Management Application, you need the following technologies and setups:

- **Java Development Kit (JDK)**: Required to compile and run Java applications. (Java 23)
- **PostgreSQL**: Relational database management system. Can be installed locally or used via a cloud service.
- **Docker**: For running the PostgreSQL database service using Docker Compose.
- **Git**: For version control and cloning the repository.
- **Node.js and npm**: Optional, for frontend development and building tasks.
- **IDE**: Integrated Development Environment (e.g., IntelliJ IDEA, Eclipse) for coding and debugging.

### Quickstart

1. **Clone the Repository**:
   ```sh
   git clone https://github.com/your-repo/n3-donation-management.git
   cd n3-donation-management
   ```

2. **Set Up the Database**:
   - Ensure Docker is installed and running.
   - Start the PostgreSQL service using Docker Compose:
     ```sh
     docker-compose up -d
     ```
   - Verify the database is running:
     ```sh
     docker ps
     ```

3. **Build and Run the Application**:
   - Build the project using Gradle:
     ```sh
     ./gradlew build
     ```
   - Run the Spring Boot application:
     ```sh
     ./gradlew bootRun
     ```

4. **Access the Application**:
   - Open the Insomnia and interact with `http://localhost:8080/donation`.

### License

Copyright (c) 2024.

This project is proprietary and not open source. All rights reserved.
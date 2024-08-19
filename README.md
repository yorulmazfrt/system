# AD Listing System

A ad listing system built with Spring Boot, React, PostgreSQL, and RabbitMQ. The system allows users to register, create, and update ad listings, while administrators can approve or reject listings. The system also includes reporting and messaging features.

## Features

- **User Registration**: Users can register with their name, phone number, email, and password.
- **Authentication**: JWT token authentication for secure login and access.
- **Admin User**: An admin user can approve or reject ad listings.
- **ad Listing Management**:
  - Users can create and update ad listings.
  - Listings are initially passive and must be approved by an admin.
- **Listing Approval**: Admins can approve or reject ad listings.
- **Recent Listings**: Service to list the last 10 approved ad listings.
- **Admin View**: Service for admins to view inactive (pending) ad listings.
- **Message Queue**: Approved ad listings are sent to a RabbitMQ message queue.
- **Reporting**: A reporting service generates reports for each ad listing.
  - Reports include details such as creation date, user, and view count.
- **Reporting API**: API for generating and updating reports based on ad listing ID.

## Technologies Used

- **Backend**: Spring Boot
- **Frontend**: React
- **Database**: PostgreSQL
- **Message Queue**: RabbitMQ
- **Authentication**: JWT

## Getting Started

### Prerequisites

- JDK 17
- Node.js and npm
- PostgreSQL
- RabbitMQ

### Installation

1. **Clone the Repository**

    ```bash
    git clone https://github.com/yorulmazfrt/system.git
    cd system
    ```

2. **Backend Setup**

    - Navigate to the `backend` directory.
    - Create a `.env` file for environment variables and configure database and RabbitMQ settings.
    - Build and run the Spring Boot application.

    ```bash
    cd system
    ./mvnw spring-boot:run
    ```

3. **Frontend Setup**

    - Navigate to the `frontend` directory.
    - Install dependencies and start the React application.

    ```bash
    cd ad-listing-frontend
    npm install
    npm start
    ```

4. **Database Setup**

    - Create a PostgreSQL database and configure the connection in the backend application.
    - Run database migrations to set up the schema.

5. **RabbitMQ Setup**

    - Install and start RabbitMQ.
    - Configure RabbitMQ connection settings in the backend application.


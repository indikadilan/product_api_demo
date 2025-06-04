# Project Name - Help

This document provides instructions for setting up, configuring, and running the [Project Name] application.

## Table of Contents (Optional, but helpful for larger files)

* [Prerequisites](#prerequisites)
* [Configuration](#configuration)
* [Running the Application](#running-the-application)
* [Accessing the Application](#accessing-the-application)


---

## Prerequisites

Before you can run the application, ensure you have the following installed and configured:

* **Java Development Kit (JDK):** Version JDK 17. Specify the version your project is built with.
    * *How to check:* `java -version`
* **Gradle:** Version 5.0+ Specify which build tool and its version.
    * *How to check (Gradle):* `gradle -version`
* **Database (if applicable):**  MySQL 5.5.0.
    * Specify if a local instance is needed or if it connects to a remote one.
    * Mention if any specific database setup or schema migration is required before running.
* **MySQL database running ** (with a user and database created)


## Configuration

Before running the application for the first time, you might need to configure certain settings:

1.  **Clone the Repository:**
    ```bash
    git clone [URL_OF_YOUR_REPOSITORY]
    cd [PROJECT_DIRECTORY_NAME]
    ```

2.  **Database Configuration (if applicable):**
    * Locate the main configuration file: `src/main/resources/application.properties` (or `application.yml`).
    * Update the following properties to match your local database setup:
        ```properties
        # Example for application.properties
        spring.datasource.url=jdbc:mysql://localhost:3306/product_catalog_db
        spring.datasource.username=your_db_user
        spring.datasource.password=your_db_password
        ```
        ```yaml
        # Example for application.yml
        spring:
          datasource:
            url: jdbc:mysql://localhost:3306/product_catalog_db
            username: your_db_user
            password: your_db_password
        ```
    * If you're using profiles (e.g., `dev`, `prod`), explain how to activate the correct profile:
        * Via IDE run configurations.
        * Via command line: `mvn spring-boot:run -Dspring-boot.run.profiles=dev` or `java -jar target/app.jar --spring.profiles.active=dev`.

3.  **Schema Initialization / Migrations (if applicable):**
    * If using Flyway or Liquibase, migrations should run automatically on startup.
    * If manual schema setup is needed: "Run the `schema.sql` script located in `src/main/resources/db/` against your database."

4.  **Other Configuration Files (if any):**
    * Mention any other files that might need local adjustments (e.g., external service URLs, API keys in config files - though secrets are better in environment variables).

---

## Running the Application

Once prerequisites are met and open with ** SpringToolSuits4 ** and run as Spring Boot App


## Accessing the Application

Once the application is running, you can access it:

* ** Main Application URL:** `http://localhost:9091/` (or your configured context path and port)

* ** Swagger UI / OpenAPI Docs (if configured):** `http://localhost:9091/swagger-ui/index.html`

* ** WEB User Interface (Check UI):** `http://localhost:9091/admin/products`

* ** API Base URL Create a User with following JSON :** `http://localhost:9091/api/users`
{
    "name": "admin",
    "email":"admin@gmail.com",
    "password":"admin",
    "roles":"ROLE_ADMIN,ROLE_USER"
} 
* ** JWT Token generation (API):** `http://localhost:9091/api/authenticate`
{
    "username": "admin",
    "password": "admin"
}
* ** Using JWT Token, check product GET:** `http://localhost:9091/api/products`
* ** Using JWT Token, check product POST,PUT,DELETE (API):** `http://localhost:9091/api/products/{id}`

Look for console output similar to this to confirm it has started:
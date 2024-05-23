# Bitcoin Price Fetcher

## Description
This project fetches the current Bitcoin price from the CoinDesk API and saves it to a MySQL database.

## Prerequisites
- Java 18+
- Maven
- MySQL

## Running the Project
1. Configure the database settings in `src/main/resources/application.properties`.
2. Run the SQL script `create_table.sql` to create the required table.
3. Build the project using Maven: `mvn clean install`.
4. Run the project: `mvn spring-boot:run`.

## API Endpoint
- `GET /api/fetch`: Fetches the current Bitcoin price and saves it to the database.

## Running Tests
- Run the unit tests using: `mvn test`.

## GitHub Repository
[Provide your GitHub repository URL here]

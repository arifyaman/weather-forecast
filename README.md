# Weather Forecast Service

## Overview

The Weather Forecast Service is a web application that provides users with access to past and future weather forecast information. Users can search for weather data by providing a start date, end date, and/or location. This service is designed to be user-friendly and provide accurate and up-to-date weather information.

## Features

- **Search by Date:** Users can search for weather forecasts for a specific date or date range by providing a start date and an optional end date.

- **Search by Location:** Users can search for weather forecasts for a specific location.

- **View Historical Data:** Users can retrieve past weather data.

- **User-Friendly Interface:** The web application features an intuitive and user-friendly interface, making it easy for users to interact with the service.

## Getting Started
If you don't have docker please check [here](#getting-started-without-docker)


If you have docker installed on your machine you can start all services with one below command.
1.
   ```bash
   docker-compose up -d
   ```

2. After you see all containers are up and running please go to http://localhost


## Getting Started Without Docker

1. Please make sure you have `JDK 17` and `PostgreSQL` environment before starting.
   
    PostgreSQL db should be running with below config
     ```
    Address: localhost:5432
    Database: weather
    Username: weather
    Password: weather
    ```
   
2. In `/weather-forecast` directory run below command to compile and run the `backend` module. `localhost` env is necessary 
   because of the `CORS` config that we will need for `web` module.
   ```bash
   ./gradlew :backend:bootRun --args='--spring.profiles.active=localhost'
   ```
    
3. In the same directory `/weather-forecast` with another terminal, run below command to run the `web` module.
    ```bash
   ./gradlew :web:run
   ```
   
    Or optionally if you have `npm` installed, in `/web/weather-check-ui` directory you can run 
   ```bash
   npm install && npm run dev
   ```
4. While two modules are up and running then go to http://localhost:5173.


## Usage

To use the Weather Forecast Service, follow these steps:

1. Open the web application in your browser.

2. Use the search form to enter the desired date, date range, and/or location.

3. View the weather forecast results on the page.

## Technologies Used

- Spring Boot
- PostgreSQL
- Vue3 Composition API
- Weather API (http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php)

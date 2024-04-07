# WeatherInformation

## Introduction

This project utilizes a specific technology stack and database to accomplish its goals. Here's a brief overview:

## Technology Stack

- **Java**: The project is developed using Java programming language, which offers robustness, platform independence, and a vast ecosystem of libraries and frameworks.

- **Spring Boot**: Spring Boot is chosen for its ease of development, convention-over-configuration approach, and built-in support for creating standalone, production-grade Spring-based applications.

- **RESTful API**: The project implements a RESTful API architecture to expose functionality over HTTP, providing a scalable and flexible approach to building web services.

-**Maven**

## Database

- **MySQL**: MySQL is selected as the database management system for storing and managing project data. MySQL is widely used, open-source, and offers features such as scalability, and high performance.

## Project Setup

-**IDE** :- Intellij

-**java version** :- 21

-Maven Installation

-Once we run the project services available on below path

{project base url}/events/find


{project base url}/events/add

## Design Decisions and Challenges Addressed

### Asynchronous Processing for Improved Performance

- **Problem**: The `findEvents` method in the `EventFindService` class retrieves events based on latitude and longitude, and then fetches weather and calculates distance for each event. However, executing these tasks sequentially can lead to increased response times due to the blocking nature of network calls.

- **Solution**: To address this challenge and improve performance, we implemented asynchronous processing using Java's `ExecutorService` and `Callable` interface. By executing weather retrieval and distance calculation tasks in parallel for each event, we were able to significantly reduce the overall execution time.

- **Implementation**: We refactored the `findEvents` method to utilize an `ExecutorService` with a fixed thread pool size, submitting tasks to fetch weather and calculate distance asynchronously for each event. This approach allowed us to leverage parallelism and maximize resource utilization, resulting in faster response times for event retrieval.

- **Outcome**: With the implementation of asynchronous processing, the `findEvents` method now executes more efficiently, delivering improved performance and responsiveness. Users can now retrieve events based on location with reduced latency, enhancing the overall user experience of the application.

-**Indexing for Performance**: Additionally, indexing was applied on the date column of the events table to optimize search performance. This indexing strategy improved the efficiency of event retrieval operations, further reducing execution time.

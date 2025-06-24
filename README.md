Flowplan Backend is a Spring Boot service that exposes a small REST API to retrieve course information from the Uniandes public course catalog and generate schedule combinations. It acts as the backend component for the Flowplan project.

## Features

- Fetch raw course data from the Uniandes API.
- Convert the raw data into domain objects (`Course`, `Section`, `Meeting`).
- Generate all possible schedules given candidate course sections.
- Simple REST endpoints implemented with Spring Web.

## Requirements

- Java 17+
- Maven 3 (a wrapper `mvnw` is provided)

## Building and Running

To build the project and run the application locally:

```bash
# build the project
./mvnw package

# run the Spring Boot application
./mvnw spring-boot:run
```

The application starts `FlowplanBackendApplication` which excludes automatic database configuration and simply launches the REST endpoints.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

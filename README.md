# Toy Tasks API (Java + Maven)

A dead-simple Java project to test DevOps tools like Jenkins, OWASP Dependency-Check, SonarQube, and Harbor.

## What this project includes

- Maven-based Java app (Spring Boot)
- In-memory task storage (no database)
- REST endpoints:
  - `GET /tasks`
  - `POST /tasks`
  - `GET /tasks/{id}`
  - `PUT /tasks/{id}`
  - `DELETE /tasks/{id}`
- Very basic unit/integration tests
- `Dockerfile`
- `docker-compose.yml`

## Requirements

- Java 17
- Maven 3.9+
- Docker + Docker Compose (optional, for container runs)

## Run locally (without Docker)

```bash
mvn spring-boot:run
```

App runs on `http://localhost:6969`.

## Test

```bash
mvn test
```

## Build JAR

```bash
mvn clean package
```

The jar will be in `target/`.

## API quick examples

Create a task:

```bash
curl -X POST http://localhost:6966/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"learn jenkins","done":false}'
```

Get all tasks:

```bash
curl http://localhost:6969/tasks
```

Get one task by id:

```bash
curl http://localhost:6969/tasks/1
```

Update task id 1:

```bash
curl -X PUT http://localhost:6969/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"learn sonarqube","done":true}'
```

Delete task id 1:

```bash
curl -X DELETE http://localhost:6969/tasks/1
```

## Run with Docker

Build image:

```bash
docker build -t toy-tasks-api:latest .
```

Run container:

```bash
docker run --rm -p 6969:6969 toy-tasks-api:latest
```

Or with Docker Compose:

```bash
docker compose up --build
```

## Why this is useful for your CI/CD tests

- **Jenkins**: build + test pipeline (`mvn test`, `mvn package`)
- **Dependency-Check**: scans Maven dependencies from `pom.xml`
- **SonarQube**: static analysis on Java source and tests
- **Harbor**: push versioned container images

That’s it—simple on purpose.

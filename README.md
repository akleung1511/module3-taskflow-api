
# TaskFlow API

A simple task management REST API built with Spring Boot as part of the NTU AI Engineering Module 3 assignment.

This project continues into the Module 4 End-of-Module Assignment: Dockerize & Deploy, where the API is containerized using Docker and deployed to a cloud hosting platform.

## Live Application URL

The TaskFlow API was successfully deployed on Render.

The initial deployment was planned for Railway, as specified in the assignment. However, Railway required a paid subscription, so Render was used instead.

**Live API:**
https://module3-taskflow-api-h255.onrender.com/api/tasks

The deployment was verified successfully, with the API returning an empty JSON array (`[]`), indicating that the application is running correctly.

## Screenshot: Deployment Dashboard

The screenshot below shows the TaskFlow API successfully deployed and running on Render.

![Render Dashboard](screenshots/render-dashboard.png)

## Screenshot: Live Application

The screenshot below shows the deployed TaskFlow API responding successfully through its public Render URL.

![Live TaskFlow API](screenshots/render-live-api.png)

## Errors and Failures

**Completed:**
- Task 1: Created the multi-stage Dockerfile.
- Task 2: Successfully built and tested the Docker image locally.
- Task 3: Pushed the Dockerfile to GitHub and deployed the application using Render.
- Task 4: Verified the live API endpoint successfully.

**Issue encountered:**

Railway deployment could not proceed because the platform required an upgrade to a paid subscription for new accounts.

**Resolution:**

Used Render's free hosting plan to deploy the same Dockerized Spring Boot application. The deployment was successful, and the live API returned `[]`.

## Stretch Goal (Optional)

**Stretch goal:** None

The optional stretch goals have not been attempted at this stage.

## Getting Started

### Prerequisites

- Java 21
- Maven (or the included Maven Wrapper)
- Docker

### Running the Application Locally

From the project root:

```bash
./mvnw spring-boot:run
```

The application starts at http://localhost:8080.

### Building the Docker Image

```bash
docker build -t taskflow:latest .
```

### Running the Docker Container

```bash
docker run -p 8080:8080 taskflow:latest
```

### Testing the API

```bash
curl http://localhost:8080/api/tasks
```

Expected response when no tasks exist:

```json
[]
```

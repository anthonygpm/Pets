# Pets

Pets is a Spring Boot application designed to manage a pet adoption platform. It provides a RESTful API for managing pets, users, and adoption requests, with integration for cloud-based image storage.

## Features

- **Pet Management**: Full CRUD (Create, Read, Update, Delete) operations for pets.
- **User Management**: Basic CRUD for platform users.
- **Adoption Process**: Create and manage adoption records, linking pets to users.
- **Image Handling**: Upload pet images to Cloudinary, with automatic deletion of old images on update or deletion of a pet.
- **Pet Search**: Search for pets by species, race, or a combination of both.
- **Database Persistence**: Uses PostgreSQL for data storage, managed via Spring Data JPA.

## Technologies Used

- **Backend**: Java 17, Spring Boot 3.5.7
- **Database**: PostgreSQL
- **Data Access**: Spring Data JPA
- **Image Storage**: Cloudinary
- **Build Tool**: Apache Maven
- **Containerization**: Docker, Docker Compose

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

- Java JDK 17 or later
- Apache Maven
- Docker and Docker Compose
- A Cloudinary account for image storage

### Installation and Configuration

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/anthonygpm/pets.git
    cd pets
    ```

2.  **Configure Environment Variables:**
    Create a `.env` file in the root directory of the project and add your Cloudinary URL:
    ```env
    CLOUDINARY_URL=cloudinary://<API_KEY>:<API_SECRET>@<CLOUD_NAME>
    ```

3.  **Start the Database:**
    Use Docker Compose to start the PostgreSQL database and pgAdmin containers.
    ```sh
    docker-compose up -d
    ```
    - The PostgreSQL database will be available on `localhost:5436`.
    - pgAdmin will be accessible at `http://localhost:8083`.

### Running the Application

Once the database is running, you can start the Spring Boot application using the Maven wrapper:

```sh
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Pets

- `GET /pets`: List all available pets.
- `GET /pets?species=<species>&race=<race>`: Search for pets by species and/or race.
- `GET /pets/{id}`: Retrieve a specific pet by its ID.
- `POST /pets`: Create a new pet.
- `PUT /pets/{id}`: Update an existing pet's details.
- `DELETE /pets/{id}`: Delete a pet.
- `POST /pets/{id}/upload-image`: Upload an image for a specific pet.

### Users

- `GET /users`: List all users.
- `GET /users/{id}`: Retrieve a specific user by ID.
- `POST /users`: Create a new user.
- `PUT /users/{id}`: Update an existing user.
- `DELETE /users/{id}`: Delete a user.

### Adoptions

- `GET /adoptions`: List all adoption records.
- `GET /adoptions/{id}`: Retrieve a specific adoption record by ID.
- `POST /adoptions`: Create a new adoption request.
  **Request Body Example:**
  ```json
  {
    "petId": 1,
    "usersId": 1,
    "adoptionStatus": "PENDING"
  }
  ```
- `PUT /adoptions/{id}`: Update the status of an adoption request.
- `DELETE /adoptions/{id}`: Delete an adoption record.

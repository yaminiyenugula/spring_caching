# Spring Boot Book Management API with Caching

## 1. Project Overview

This project is a simple REST API built using Spring Boot. It demonstrates how to create a layered application and use Spring's caching mechanism to improve performance.

The application allows users to:
- Retrieve a book using its ISBN
- Add a new book
- Update an existing book
- Delete a book

It uses an in-memory repository and Spring's cache abstraction to demonstrate the basic caching flow.

## 2. Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Cache
- Maven
- REST API
- Postman

## 3. Project Architecture

The application follows a layered architecture:

Controller → Service → Repository → In-memory data store

### Controller Layer

Receives HTTP requests from the client and returns HTTP responses.

File: `BookController.java`

### Service Layer

Contains the business logic and caching operations.

File: `BookService.java`

### Repository Layer

Handles book data retrieval, insertion, updating, and deletion.

Files:
- `BookRepository.java`
- `SimpleBookRepository.java`

### Model

Represents the book data.

File: `Book.java`

## 4. How Caching Works

The application uses Spring Cache annotations.

### @EnableCaching

Enables caching support in the Spring Boot application.

### @Cacheable

Used in the `getBook()` method.

- Checks whether the requested ISBN is already in the cache.
- If found, returns the cached book.
- If not found, calls the repository and stores the result in the cache.

### @CachePut

Used in `addBook()` and `updateBook()`.

- Executes the method every time.
- Stores the returned book in the cache.
- Refreshes the cache after adding or updating a book.

### @CacheEvict

Used in `deleteBook()`.

- Removes the corresponding book entry from the cache.

## 5. API Endpoints

Base URL: `http://localhost:8080/api/books`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/isbn-1234` | Retrieve a book |
| POST | `/` | Add a book |
| PUT | `/isbn-1234` | Update a book |
| DELETE | `/isbn-1234` | Delete a book |

### GET - Retrieve a Book

`GET http://localhost:8080/api/books/isbn-1234`

Example response:

```json
{
  "isbn": "isbn-1234",
  "title": "Spring Boot in Action"
}
```

Call the same endpoint twice. The first request should take approximately 3 seconds. The second request should be faster because the book is cached.

### POST - Add a Book

`POST http://localhost:8080/api/books`

Request body:

```json
{
  "isbn": "isbn-7890",
  "title": "Microservices with Spring Boot"
}
```

### PUT - Update a Book

`PUT http://localhost:8080/api/books/isbn-1234`

Request body:

```json
{
  "isbn": "isbn-1234",
  "title": "Advanced Spring Boot"
}
```

### DELETE - Delete a Book

`DELETE http://localhost:8080/api/books/isbn-1234`

## 6. How to Run the Project

1. Clone or download the repository.
2. Open the project in your IDE.
3. Make sure Java 17 or the configured Java version is installed.
4. Run the following command:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on:

`http://localhost:8080`

## 7. Run Tests

On Windows PowerShell:

```powershell
.\mvnw.cmd clean test
```

The integration tests verify:
- Book retrieval and cache creation
- Cache refresh after updating a book
- Cache removal after deleting a book

## 8. Data Storage and Cache

This is a learning/demo project.

- The repository stores books in a `ConcurrentHashMap`.
- Spring's default cache implementation stores cached entries in memory.
- Both are in-memory; data is not persisted in a database.
- Data is lost when the application restarts.

## 9. Possible Improvements

- Connect the application to MySQL or PostgreSQL.
- Use Redis for distributed caching.
- Add input validation and exception handling.
- Add pagination and search.
- Add logging and monitoring.

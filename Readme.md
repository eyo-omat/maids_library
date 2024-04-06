# Library System API Documentation
Welcome to the documentation for the Library System API. This API provides endpoints to manage books, patrons, and borrowing records in a library system.

## Start up
Run the below command to start up the project

`mvn spring-boot:run`

## Base URL
The base URL for all API endpoints is: http://localhost:8878/api

## The Service JSON REST APIs:
> Books
- GET /books: Get all books
- GET /books/{id}: Get a book by ID
- POST /books: Add a new book
- PUT /books/{id}: Update an existing book
- DELETE /books/{id}: Delete a book by ID
> 
> Patrons
- GET /patrons: Get all patrons
- GET /patrons/{id}: Get a patron by ID
- POST /patrons: Add a new patron
- PUT /patrons/{id}: Update an existing patron
- DELETE /patrons/{id}: Delete a patron by ID

> Borrowing Records
- POST /borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book
- PUT /return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron

## Error Handling
If a request results in an error, an appropriate HTTP status code and error message will be returned in the response body.

##Sample Usage
Get All Books [/api/books]()

**Sample Response**
`[
{
"id": 1,
"title": "The Great Gatsby",
"author": "F. Scott Fitzgerald",
"publicationYear": 1925,
"isbn": "9780743273565"
},
{
"id": 2,
"title": "To Kill a Mockingbird",
"author": "Harper Lee",
"publicationYear": 1960,
"isbn": "9780061120084"
}
]`
***
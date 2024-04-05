package com.maids.maids_library.controller;

import com.maids.maids_library.dto.BookRequest;
import com.maids.maids_library.model.Book;
import com.maids.maids_library.service.BookService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/books")
@RequiredArgsConstructor
public class BookController {

    @NonNull
    BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> fetchAllBooks() {
        List<Book> allBooks = bookService.fetchAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<Book> fetchBook(@Valid @PathVariable long bookId) {
        Book book = bookService.fetchBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookRequest bookRequest) {
        Book newBook = bookService.addBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping(value = "/{book_id}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody BookRequest bookRequest, @PathVariable Long book_id) {
        Book updatedBook = bookService.updateBook(bookRequest, book_id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @DeleteMapping(value = "/{book_id}")
    public ResponseEntity<String> removeBook(@Valid @PathVariable Long book_id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookService.removeBook(book_id));
    }

}

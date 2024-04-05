package com.maids.maids_library.service;

import com.maids.maids_library.dto.BookRequest;
import com.maids.maids_library.exceptions.BookNotFoundException;
import com.maids.maids_library.exceptions.DuplicateISBNException;
import com.maids.maids_library.model.Book;
import com.maids.maids_library.repository.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @NonNull
    BookRepository bookRepository;

    public Book addBook(BookRequest book) {
        // Check if a book exists with the provided ISBN, ISBNs should be unique
        Book existingIsbn = bookRepository.findBookByIsbn(book.isbn);

        if (existingIsbn != null) {
            // Book exists throw a validation error
            throw new DuplicateISBNException("Book with isbn: " + book.isbn + " already exists");
        }
        // Proceed to save new book
        Book newBook = new Book();
        newBook.setAuthor(book.author);
        newBook.setTitle(book.title);
        newBook.setPublicationYear(book.publicationYear);
        newBook.setIsbn(book.isbn);
        bookRepository.save(newBook);

        return newBook;
    }

    public List<Book> fetchAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "books")
    public Book fetchBook(long book_id) {
        return bookRepository.findById(book_id).orElseThrow(() -> new BookNotFoundException("Book with provided id: " + book_id + " not found"));
    }

    @Transactional
    public Book updateBook(BookRequest bookRequest, Long book_id) {
        return bookRepository.findById(book_id)
                .map(existingBook -> {
                    existingBook.setTitle(bookRequest.title);
                    existingBook.setAuthor(bookRequest.author);
                    existingBook.setIsbn(bookRequest.isbn);
                    existingBook.setPublicationYear(bookRequest.publicationYear);
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new BookNotFoundException("Book with provided id: " + book_id + " not found"));
    }

    public String removeBook(Long book_id) {
        return bookRepository.findById(book_id)
                .map(book -> {
                    bookRepository.deleteById(book_id);
                    return "Book deleted successfully";
                }).orElseThrow(() -> new BookNotFoundException("Book with provided id: " + book_id + " not found"));
    }
}

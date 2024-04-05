package com.maids.maids_library.service;

import com.maids.maids_library.dto.BookRequest;
import com.maids.maids_library.model.Book;
import com.maids.maids_library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    public Book addBook(BookRequest book) {
        Book existingIsbn = bookRepository.findBookByIsbn(book.isbn);

        if (existingIsbn != null) {
            // Book exists throw a validation error
        }
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

    public Book fetchBook(long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
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
                .orElse(null);
    }

    public String removeBook(Long book_id) {
        return bookRepository.findById(book_id)
                .map(book -> {
                    bookRepository.deleteById(book_id);
                    return "Book deleted successfully";
                }).orElse("Book does not exist");
    }
}

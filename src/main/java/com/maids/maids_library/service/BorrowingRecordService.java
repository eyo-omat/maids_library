package com.maids.maids_library.service;

import com.maids.maids_library.exceptions.BookNotFoundException;
import com.maids.maids_library.exceptions.BorrowingRecordNotFoundException;
import com.maids.maids_library.exceptions.PatronNotFoundException;
import com.maids.maids_library.model.Book;
import com.maids.maids_library.model.BorrowingRecord;
import com.maids.maids_library.model.Patron;
import com.maids.maids_library.repository.BookRepository;
import com.maids.maids_library.repository.BorrowingRecordRepository;
import com.maids.maids_library.repository.PatronRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BorrowingRecordService {

    @NonNull
    BorrowingRecordRepository borrowingRecordRepository;

    @NonNull
    BookRepository bookRepository;

    @NonNull
    PatronRepository patronRepository;

    @Transactional
    public BorrowingRecord borrowBook(Long book_id, Long patron_id) {
        Book book = bookRepository.findById(book_id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + book_id));

        Patron patron = patronRepository.findById(patron_id)
                .orElseThrow(() -> new PatronNotFoundException("Patron not found with id: " + patron_id));

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(new Date());

        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron not found with id: " + patronId));

        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron)
                .orElseThrow(() -> new BorrowingRecordNotFoundException("No active borrowing record found for bookId: " + bookId + " and patronId: " + patronId));

        borrowingRecord.setReturnDate(new Date());

        return borrowingRecordRepository.save(borrowingRecord);
    }
}

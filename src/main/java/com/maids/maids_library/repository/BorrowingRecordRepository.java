package com.maids.maids_library.repository;

import com.maids.maids_library.model.Book;
import com.maids.maids_library.model.BorrowingRecord;
import com.maids.maids_library.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
}

package com.maids.maids_library.repository;

import com.maids.maids_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByIsbn(String isbn);
}

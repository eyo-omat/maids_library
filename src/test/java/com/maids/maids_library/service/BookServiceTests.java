package com.maids.maids_library.service;

import com.maids.maids_library.model.Book;
import com.maids.maids_library.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testGetBookById() {
        Long bookId = 1L;
        Book book = new Book();
        book.setBook_id(bookId);
        book.setTitle("Sample Book");
        book.setAuthor("John Doe");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book result = bookService.fetchBook(bookId);

        assertThat(result).isNotNull();
        assertThat(result.getBook_id()).isEqualTo(bookId);
        assertThat(result.getTitle()).isEqualTo("Sample Book");
        assertThat(result.getAuthor()).isEqualTo("John Doe");
    }
}

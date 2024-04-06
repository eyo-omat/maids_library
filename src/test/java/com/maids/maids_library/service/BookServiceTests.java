package com.maids.maids_library.service;

import com.maids.maids_library.model.Book;
import com.maids.maids_library.repository.BookRepository;

import java.util.Optional;

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
        book.setId(bookId);
        book.setTitle("Sample Book");
        book.setAuthor("John Doe");

        given(bookRepository.findById(bookId)).willReturn(Optional.of(book));

        Book result = bookService.getBookById(bookId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(bookId);
        assertThat(result.getTitle()).isEqualTo("Sample Book");
        assertThat(result.getAuthor()).isEqualTo("John Doe");
    }
}

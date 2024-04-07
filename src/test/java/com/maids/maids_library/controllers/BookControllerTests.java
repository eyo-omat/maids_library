package com.maids.maids_library.controllers;


import com.maids.maids_library.model.Book;
import com.maids.maids_library.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetBookById() throws Exception {
        Long bookId = 1L;
        Book book = new Book();
        book.setBook_id(bookId);
        book.setTitle("Sample Book");
        book.setAuthor("John Doe");

        when(bookService.fetchBook(bookId)).thenReturn(book);

        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.book_id").value(book.getBook_id().intValue()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()));
    }
}

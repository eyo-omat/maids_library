package com.maids.maids_library.controllers;


import com.maids.maids_library.model.Book;
import com.maids.maids_library.service.BookService;

@RunWith(SpringRunner.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetBookById() throws Exception {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Sample Book");
        book.setAuthor("John Doe");

        given(bookService.getBookById(bookId)).willReturn(book);

        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId().intValue())))
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())));
    }
}

package com.maids.maids_library.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    private String title;

    private String author;

    private int publicationYear;

    @Column(unique = true)
    private String isbn;
}

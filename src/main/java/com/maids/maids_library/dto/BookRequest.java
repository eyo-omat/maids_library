package com.maids.maids_library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "A book title is required and cannot be blank")
    public String title;

    @NotBlank(message = "A book author is required and cannot be blank")
    public String author;

    @NotBlank(message = "Publication year of a book is required and cannot be blank")
    public int publicationYear;

    @NotBlank(message = "A book ISBN is required and cannot be blank")
    public String isbn;
}

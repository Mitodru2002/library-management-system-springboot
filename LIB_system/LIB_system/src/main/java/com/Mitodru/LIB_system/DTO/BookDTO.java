package com.Mitodru.LIB_system.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long bookId;

    @NotBlank(message = "Book title is required")
    @Size(min = 2, max = 150, message = "Title must be between 2 and 150 characters")
    private String title;

    @NotBlank(message = "Author name is required")
    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
    private String author;

    @NotBlank(message = "Author name is required")
    @Size(min = 2, max = 150, message = "Author name must be between 2 and 100 characters")
    private String publisher;

    @NotBlank(message = "Author name is required")
    @Size(min = 2, max = 150, message = "Author name must be between 2 and 100 characters")
    private String category;

    @Min(value = 1, message = "Copies must be at least 1")
    private int totalCopies;

    @Min(value = 1, message = "Copies must be at least 1")
    private int availableCopies;
}


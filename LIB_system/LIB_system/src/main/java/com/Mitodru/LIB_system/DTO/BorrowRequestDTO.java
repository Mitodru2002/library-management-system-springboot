package com.Mitodru.LIB_system.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BorrowRequestDTO {

    @NotNull(message = "Student ID is mandatory")
    private Long studentId;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;
}

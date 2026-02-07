package com.Mitodru.LIB_system.DTO;

import com.Mitodru.LIB_system.enums.BorrowStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRecordDTO {

    private Long id;
    private Long studentId;
    private Long bookId;
    private String bookTitle;

    private LocalDate issueDate;
    private LocalDate returnDate;

    private BorrowStatus status;
    private Long finePrice;
}

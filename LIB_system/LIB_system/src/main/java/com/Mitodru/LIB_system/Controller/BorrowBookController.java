package com.Mitodru.LIB_system.Controller;



import com.Mitodru.LIB_system.DTO.BorrowRequestDTO;
import com.Mitodru.LIB_system.Service.BookBorrowService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowBookController {

    private final BookBorrowService borrowBookService;

    public BorrowBookController(BookBorrowService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<String> borrowBook(
            @Valid @RequestBody BorrowRequestDTO request) {

        return ResponseEntity.ok(borrowBookService.borrowBook(request));
    }

    @DeleteMapping("/returnBook/{borrowRecordId}")
    public ResponseEntity<String> returnBook(
            @PathVariable Long borrowRecordId) {

        return ResponseEntity.ok(borrowBookService.returnBook(borrowRecordId));
    }
}



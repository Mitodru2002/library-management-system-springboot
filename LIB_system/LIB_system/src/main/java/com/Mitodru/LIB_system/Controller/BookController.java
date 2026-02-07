package com.Mitodru.LIB_system.Controller;


import com.Mitodru.LIB_system.DTO.BookDTO;
import com.Mitodru.LIB_system.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/getAllBooks")
    public List<BookDTO> getAllBooks() {
        return bookService.returnAllBooks();
    }

    @PostMapping("/addBooks")
    public BookDTO addBooks(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.addBooks(bookDTO);
    }

    @PatchMapping("/updateBook")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(bookDTO);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public String deleteBook(@Valid @PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }

}

package com.Mitodru.LIB_system.Service;

import com.Mitodru.LIB_system.Controller.BookController;
import com.Mitodru.LIB_system.DTO.BookDTO;
import com.Mitodru.LIB_system.Model.Book;
import com.Mitodru.LIB_system.Repository.BookRepo;
import com.Mitodru.LIB_system.Utils.BookConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookConverter bookConverter;

    @Autowired
    BookRepo bookRepo;

    public BookDTO addBooks(BookDTO bookDTO) {
        Book book = new Book();
        if(bookDTO != null) {
            book = bookConverter.convertBookDTOtoEntity(bookDTO);
            bookRepo.save(book);
        }
        return bookConverter.convertEntityToBookDTO(book);
    }

    public List<BookDTO> returnAllBooks() {
        List<Book> allBooks = bookRepo.findAll();
        List<BookDTO> allBookDTO = new ArrayList<>();
        for(Book book : allBooks) {
            allBookDTO.add(bookConverter.convertEntityToBookDTO(book));
        }
        return allBookDTO;
    }

    public ResponseEntity<?> updateBook(@Valid BookDTO bookDTO) {

        Optional<Book> optionalBook = bookRepo.findById(bookDTO.getBookId());

        if (optionalBook.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Book not found with ID: " + bookDTO.getBookId());
        }

        Book book = optionalBook.get();

        book = bookConverter.convertBookDTOtoEntity(bookDTO);
        bookRepo.save(book);

        return ResponseEntity.ok(bookDTO);
    }

    public String deleteBook(@Valid Long bookId) {
        Optional<Book> optionalBook = bookRepo.findById(bookId);

        if (optionalBook.isEmpty()) {
            return "Book not found with ID: " + bookId;
        }

        bookRepo.deleteById(bookId);
        return "Book deleted successfully with ID: " + bookId;
    }
}

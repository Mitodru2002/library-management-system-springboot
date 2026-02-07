package com.Mitodru.LIB_system.Utils;


import com.Mitodru.LIB_system.DTO.BookDTO;
import com.Mitodru.LIB_system.Model.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    public Book convertBookDTOtoEntity(BookDTO bookDTO) {
        Book book = new Book();
        if(bookDTO != null) {
            BeanUtils.copyProperties(bookDTO, book);
        }
        return book;
    }
    public BookDTO convertEntityToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        if(book != null) {
            BeanUtils.copyProperties(book, bookDTO);
        }
        return bookDTO;
    }
}

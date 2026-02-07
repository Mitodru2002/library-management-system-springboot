package com.Mitodru.LIB_system.Utils;



import com.Mitodru.LIB_system.DTO.BorrowRecordDTO;
import com.Mitodru.LIB_system.Model.Book;
import com.Mitodru.LIB_system.Model.BorrowRecord;
import com.Mitodru.LIB_system.Model.Student;
import org.springframework.stereotype.Component;

@Component
public class BorrowRecordConverter {
    public BorrowRecordDTO convertEntityToDTO(BorrowRecord borrowRecord) {
        BorrowRecordDTO borrowRecordDTO = new BorrowRecordDTO();
        Student student = borrowRecord.getStudent();
        Book book = borrowRecord.getBook();
        if(borrowRecord != null) {
            borrowRecordDTO.setBookId(book.getBookId());
            borrowRecordDTO.setStatus(borrowRecord.getStatus());
            borrowRecordDTO.setIssueDate(borrowRecord.getIssueDate());
            borrowRecordDTO.setReturnDate(borrowRecord.getReturnDate());
            borrowRecordDTO.setBookTitle(book.getTitle());
            borrowRecordDTO.setStudentId(student.getStudentId());

        }
        return borrowRecordDTO;
    }
}

package com.Mitodru.LIB_system.Service;



import com.Mitodru.LIB_system.DTO.BorrowRequestDTO;
import com.Mitodru.LIB_system.Model.Book;
import com.Mitodru.LIB_system.Model.BorrowRecord;
import com.Mitodru.LIB_system.Model.Student;
import com.Mitodru.LIB_system.Repository.BookRepo;
import com.Mitodru.LIB_system.Repository.BorrowRecordRepo;
import com.Mitodru.LIB_system.Repository.StudentRepo;
import com.Mitodru.LIB_system.enums.BorrowStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookBorrowService {

    private final StudentRepo studentRepo;
    private final BookRepo bookRepo;
    private final BorrowRecordRepo borrowRecordRepo;

    public BookBorrowService(StudentRepo studentRepo,
                             BookRepo bookRepo,
                             BorrowRecordRepo borrowRecordRepo) {
        this.studentRepo = studentRepo;
        this.bookRepo = bookRepo;
        this.borrowRecordRepo = borrowRecordRepo;

    }

    public String borrowBook(BorrowRequestDTO request) {

        // 1️⃣ Validate student
        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 2️⃣ Validate book
        Book book = bookRepo.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 3️⃣ Check availability
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        // 4️⃣ Create borrow record
        BorrowRecord record = new BorrowRecord();
        record.setStudent(student);
        record.setBook(book);
        record.setIssueDate(LocalDate.now());
        record.setReturnDate(LocalDate.now().plusDays(15)); // ✅ AUTO RETURN DATE
        record.setStatus(BorrowStatus.BORROWED);
        record.setFinePrice(0L);

        borrowRecordRepo.save(record);

        // 5️⃣ Update book availability
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        return "Book borrowed successfully. Return by "
                + record.getReturnDate();
    }

    @Transactional
    public String returnBook(Long borrowRecordId) {

        BorrowRecord record = borrowRecordRepo.findById(borrowRecordId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        Book book = record.getBook();

        // ✅ Increase available copies
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        // ✅ Delete borrow record
        borrowRecordRepo.delete(record);

        return "Book returned successfully";
    }
}


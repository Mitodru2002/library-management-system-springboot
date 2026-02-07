package com.Mitodru.LIB_system.Service;



import com.Mitodru.LIB_system.DTO.BorrowRecordDTO;
import com.Mitodru.LIB_system.DTO.StudentDTO;
import com.Mitodru.LIB_system.DTO.StudentReg;
import com.Mitodru.LIB_system.Model.Book;
import com.Mitodru.LIB_system.Model.BorrowRecord;
import com.Mitodru.LIB_system.Model.Student;
import com.Mitodru.LIB_system.Repository.BookRepo;
import com.Mitodru.LIB_system.Repository.BorrowRecordRepo;
import com.Mitodru.LIB_system.Repository.StudentRepo;
import com.Mitodru.LIB_system.Utils.BorrowRecordConverter;
import com.Mitodru.LIB_system.Utils.StudentConverter;
import com.Mitodru.LIB_system.enums.BorrowStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private BorrowRecordRepo borrowRecordRepo;

    @Autowired
    private BorrowRecordConverter borrowRecordConverter;

    @Autowired
    private BookRepo bookRepo;

    // REGISTER
    public StudentDTO studentRegistration(StudentReg studentReg) {
        Student student = studentConverter.convertStudentRegToEntity(studentReg);
        studentRepo.save(student);
        return studentConverter.convertEntityToStudentDTO(student);
    }

    // READ ALL
    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> list = new ArrayList<>();
        for (Student s : studentRepo.findAll()) {
            list.add(studentConverter.convertEntityToStudentDTO(s));
        }
        return list;
    }

    // READ BORROW RECORDS
    @Transactional
    public List<BorrowRecordDTO> showBorrowRecords(Long studentId) {

        List<BorrowRecord> records =
                borrowRecordRepo.findByStudentStudentId(studentId);

        List<BorrowRecordDTO> result = new ArrayList<>();

        for (BorrowRecord br : records) {

            if (br.getReturnDate().isBefore(LocalDate.now())
                    && br.getStatus() == BorrowStatus.BORROWED) {
                br.setStatus(BorrowStatus.OVERDUE);
            }

            if (br.getStatus() == BorrowStatus.OVERDUE) {
                br.setFinePrice(
                        ChronoUnit.DAYS.between(br.getReturnDate(), LocalDate.now())
                );
            }

            borrowRecordRepo.save(br); // ✅ persist changes
            result.add(borrowRecordConverter.convertEntityToDTO(br));
        }

        return result;
    }

    // UPDATE STUDENT
    @Transactional
    public StudentDTO updateStudent(StudentDTO dto) {

        if (dto.getStudentId() == null) {
            throw new RuntimeException("Student ID is required");
        }

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (dto.getName() != null) student.setName(dto.getName());
        if (dto.getRollNumber() != null) student.setRollNumber(dto.getRollNumber());
        if (dto.getEmail() != null) student.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) student.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getDepartment() != null) student.setDepartment(dto.getDepartment());

        return studentConverter.convertEntityToStudentDTO(student);
    }

    // DELETE STUDENT
    @Transactional
    public ResponseEntity<String> deleteStudent(Long studentId) {

        if (!studentRepo.existsById(studentId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }

        List<BorrowRecord> records =
                borrowRecordRepo.findByStudentStudentId(studentId);

        for (BorrowRecord br : records) {
            Book book = br.getBook();
            if (book != null) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                bookRepo.save(book);
            }
        }

        borrowRecordRepo.deleteAll(records);
        studentRepo.deleteById(studentId);

        return ResponseEntity.ok("Student deleted successfully");
    }
}

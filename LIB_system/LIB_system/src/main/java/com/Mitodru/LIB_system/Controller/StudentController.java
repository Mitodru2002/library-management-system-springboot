package com.Mitodru.LIB_system.Controller;


import com.Mitodru.LIB_system.DTO.BorrowRecordDTO;
import com.Mitodru.LIB_system.DTO.StudentDTO;
import com.Mitodru.LIB_system.DTO.StudentReg;
import com.Mitodru.LIB_system.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/studentRegister")
    public StudentDTO registerStudent(@RequestBody StudentReg studentReg) {
        return studentService.studentRegistration(studentReg);
    }

    @GetMapping("/getAllStudents")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PatchMapping("/updateStudent")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentDTO);
    }

    @GetMapping("/borrow-records/{studentId}")
    public List<BorrowRecordDTO> showBorrowRecords(@PathVariable Long studentId) {
        return studentService.showBorrowRecords(studentId);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }
}

package com.Mitodru.LIB_system.Utils;



import com.Mitodru.LIB_system.DTO.StudentDTO;
import com.Mitodru.LIB_system.DTO.StudentReg;
import com.Mitodru.LIB_system.Model.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    public Student convertStudentRegToEntity(StudentReg studentReg) {
        Student student = new Student();
        if (studentReg != null) {
            BeanUtils.copyProperties(studentReg, student);
        }
        return student;
    }
    public StudentDTO convertEntityToStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        BeanUtils.copyProperties(student, dto);
        return dto;
    }
}

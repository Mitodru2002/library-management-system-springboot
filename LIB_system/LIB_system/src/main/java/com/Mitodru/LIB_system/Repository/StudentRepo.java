package com.Mitodru.LIB_system.Repository;



import com.Mitodru.LIB_system.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {

}

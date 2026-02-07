package com.Mitodru.LIB_system.Repository;



import com.Mitodru.LIB_system.Model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BorrowRecordRepo extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByStudentStudentId(Long studentId);
}

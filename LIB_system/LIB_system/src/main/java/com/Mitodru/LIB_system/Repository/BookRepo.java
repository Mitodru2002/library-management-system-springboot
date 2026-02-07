package com.Mitodru.LIB_system.Repository;



import com.Mitodru.LIB_system.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {

}

package com.example.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Modifying
    @Query("UPDATE Book b SET b.status = :status WHERE b.id IN :ids")
    int updateBookStatusBulk(String status, List<Long> ids);
}

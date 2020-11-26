package com.libraryManagement.repository;

import com.libraryManagement.model.Book;
import com.libraryManagement.model.DTO.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("select b from Book b where b.author like %?1% group by b.author")
    Page<Book> findByAuthor(String author, Pageable pageable);
}

package com.amit.LibManagement.repositary;

import com.amit.LibManagement.model.Book;
import com.amit.LibManagement.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);
    List<Book> findByTitleContaining(String title);
    List<Book> findByBookType(BookType bookType);
//    List<Book> findByAuthorName(String authorName);
}

package com.amit.LibManagement.service;

import com.amit.LibManagement.dto.BookRequest;
import com.amit.LibManagement.model.Author;
import com.amit.LibManagement.model.Book;
import com.amit.LibManagement.repositary.AuthorRepository;
import com.amit.LibManagement.repositary.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(BookRequest bookRequest) {
        Author authorFromDB = authorRepository.getAuthorByEmail(bookRequest.getAuthorEmail());

        if(authorFromDB == null){
            authorFromDB= authorRepository.save(bookRequest.toAuthor());
        }

        Book book = bookRequest.toBook();
        book.setAuthor(authorFromDB);
        return bookRepository.save(book);
    }
}

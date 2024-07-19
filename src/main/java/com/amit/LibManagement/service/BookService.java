package com.amit.LibManagement.service;

import com.amit.LibManagement.dto.BookRequest;
import com.amit.LibManagement.model.*;
import com.amit.LibManagement.repositary.AuthorRepository;
import com.amit.LibManagement.repositary.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Book> filter(FilterType filterType, Operator operator, String value) {
        switch (filterType){
            case BOOK_TITLE :
                switch (operator){
                    case EQUAlS :
                        return bookRepository.findByTitle(value);
                    case LIKE:
                        return bookRepository.findByTitleContaining(value);
                    default:
                        return new ArrayList<>();
                }
            case BOOK_TYPE:
                switch (operator){
                    case EQUAlS :
                        return bookRepository.findByBookType(BookType.valueOf(value));
                }
//            case AUTHOR_NAME:
//                switch (operator){
//                    case EQUAlS :
//                        return
//                }
        }
        return new ArrayList<>();
    }
}

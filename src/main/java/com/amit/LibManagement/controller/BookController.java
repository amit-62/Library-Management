package com.amit.LibManagement.controller;

import com.amit.LibManagement.dto.BookRequest;
import com.amit.LibManagement.model.Book;
import com.amit.LibManagement.model.FilterType;
import com.amit.LibManagement.model.Operator;
import com.amit.LibManagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public Book addBook(@RequestBody @Valid BookRequest bookRequest){
        // validation done by BookRequest using validation jar
        // calling service
        Book book = bookService.addBook(bookRequest);

        return book;
    }

    @GetMapping("/filter")
    public List<Book> filter(@RequestParam("filterBy")FilterType filterType,
                                 @RequestParam("operator")Operator operator,
                                 @RequestParam("value") String value){
        return bookService.filter(filterType, operator, value);
    }
}


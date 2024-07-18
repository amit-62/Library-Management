package com.amit.LibManagement.service;

import com.amit.LibManagement.model.Author;
import com.amit.LibManagement.repositary.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author getAuthorData(String email) {
        return authorRepository.getAuthorByEmail(email);
    }
}

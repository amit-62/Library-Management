package com.amit.LibManagement.controller;

import com.amit.LibManagement.dto.BookRequest;
import com.amit.LibManagement.dto.UserRequest;
import com.amit.LibManagement.model.User;
import com.amit.LibManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addStudent")
    public User addStudent(@RequestBody @Valid UserRequest userRequest){
        User user = userService.addStudent(userRequest);

        return user;
    }

    @PostMapping("/addAdmin")
    public User addAdmin(@RequestBody @Valid UserRequest userRequest){
        User user = userService.addAdmin(userRequest);

        return user;
    }
}

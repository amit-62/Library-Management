package com.amit.LibManagement.service;

import com.amit.LibManagement.dto.UserRequest;
import com.amit.LibManagement.model.User;
import com.amit.LibManagement.model.UserType;
import com.amit.LibManagement.repositary.UserRepositary;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepositary userRepositary;

    public User addAdmin(@Valid UserRequest userRequest) {
//        User user = userRequest.toUser();
//        user.setUserType(UserType.ADMIN);
//        return userRepositary.save(user);
        return  null;
    }

    public User addStudent(@Valid UserRequest userRequest) {
        User user = userRequest.toUser();
        user.setUserType(UserType.STUDENT);
        return userRepositary.save(user);
    }

    public User getStudentByPhoneNo(String userPhoneNo){
        return userRepositary.findByPhoneNoAndUserType(userPhoneNo, UserType.STUDENT);
    }
}

package com.amit.LibManagement.service;

import com.amit.LibManagement.dto.TxnRequest;
import com.amit.LibManagement.model.User;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;

public class TxnService {

    @Autowired
    private UserService userService;

    public String create(TxnRequest txnRequest) {
        User userFromDB = userService.getStudentByPhoneNo(txnRequest.getUserPhoneNo());
        if(userFromDB == null){

        }
    }
}

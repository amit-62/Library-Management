package com.amit.LibManagement.controller;

import com.amit.LibManagement.dto.TxnRequest;
import com.amit.LibManagement.service.TxnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/create")
    public String create(@RequestBody @Valid TxnRequest txnRequest){
        return txnService.create(txnRequest);
    }
}

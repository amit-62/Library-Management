package com.amit.LibManagement.controller;

import com.amit.LibManagement.dto.TxnRequest;
import com.amit.LibManagement.service.TxnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody @Valid TxnRequest txnRequest) throws Exception {
        String txnId = txnService.create(txnRequest);
        return new ResponseEntity<>(txnId, HttpStatus.OK);
    }

    @PutMapping("/return")
    public ResponseEntity<Integer> returnBook(@RequestBody @Valid TxnRequest txnRequest) throws Exception {
        int fine = txnService.returnBook(txnRequest);
        return new ResponseEntity<>(fine, HttpStatus.OK);
    }
}

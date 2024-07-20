package com.amit.LibManagement.service;

import com.amit.LibManagement.dto.TxnRequest;
import com.amit.LibManagement.exception.TxnException;
import com.amit.LibManagement.model.*;
import com.amit.LibManagement.repositary.TxnRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TxnService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TxnRepository txnRepository;

    @Transactional(rollbackOn = {TxnException.class})
    public String create(TxnRequest txnRequest) {
        User userFromDB = userService.getStudentByPhoneNo(txnRequest.getUserPhoneNo());
        if(userFromDB == null){
            new TxnException("student does not belong to library");
        }

        List<Book> books = bookService.filter(FilterType.BOOK_NO, Operator.EQUAlS, txnRequest.getBookNo());
        if(books.isEmpty()){
            new TxnException("student does not belong to library");
        }
        Book bookFromDB = books.get(0);
        if(bookFromDB.getUser()!=null){
            new TxnException("book is issued");
        }
        String txnId = UUID.randomUUID().toString();
        Txn txn = Txn.builder().
                txnId(txnId).
                user(userFromDB).
                book(bookFromDB).
                txnStatus(TxnStatus.ISSUED).
                build();
        txnRepository.save(txn);
        bookFromDB.setUser(userFromDB);
        bookService.updateBookData(bookFromDB);
        return txnId;
    }
}

package com.amit.LibManagement.service;

import com.amit.LibManagement.dto.TxnRequest;
import com.amit.LibManagement.exception.TxnException;
import com.amit.LibManagement.model.*;
import com.amit.LibManagement.repositary.TxnRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TxnRepository txnRepository;

    @Value("$(book.valid.days)")
    private int validDays;

    @Value("$(book.fine.per.day)")
    private int fineAmt;

    private User getUserFromDB(TxnRequest txnRequest) throws Exception {
        User userFromDB = userService.getStudentByPhoneNo(txnRequest.getUserPhoneNo());
        if(userFromDB == null){
            throw new TxnException("student does not belong to library");
        }
        return userFromDB;
    }

    private Book getBookFromDB(TxnRequest txnRequest) throws Exception {
        List<Book> books = bookService.filter(FilterType.BOOK_NO, Operator.EQUAlS, txnRequest.getBookNo());
        if(books.isEmpty()){
            throw new TxnException("student does not belong to library");
        }
        //        if(bookFromDB.getUser()!=null){
//            throw new TxnException("book is issued");
//        }
        return books.get(0);
    }

    @Transactional(rollbackOn = {TxnException.class})
    private String createTxn(User userFromDB, Book bookFromDB) {
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


    public String create(TxnRequest txnRequest) throws Exception {
        User userFromDB = getUserFromDB(txnRequest);

        Book bookFromDB = getBookFromDB(txnRequest);
        if(bookFromDB.getUser()!=null){
            throw new TxnException("book is issued");
        }
        return createTxn(userFromDB, bookFromDB);
    }


    public int returnBook(TxnRequest txnRequest) throws Exception {
        User userFromDB = getUserFromDB(txnRequest);
        Book bookFromDB = getBookFromDB(txnRequest);

        if(bookFromDB.getUser()!=userFromDB){
            throw new TxnException("this is not the user whom book was issued to");
        }
        Txn txn = txnRepository.getUserPhoneNoAndBookBookNoAndTxnStatus(txnRequest.getUserPhoneNo(), txnRequest.getBookNo(), TxnStatus.ISSUED);
        int fine = calculateFine(txn, bookFromDB.getSecurityAmount());
        if(fine == bookFromDB.getSecurityAmount()){
            txn.setTxnStatus(TxnStatus.RETURNED);
        }
        else {
            txn.setTxnStatus(TxnStatus.FINED);
        }
        txn.setSettlementAmount(fine);
        bookFromDB.setUser(null);
        bookService.updateBookData(bookFromDB);
        return fine;
    }

    private int calculateFine(Txn txn, int securityAmount) {
        long issueDate = txn.getCreatedOn().getTime();
        long present = System.currentTimeMillis();

        long diffInMillis = Math.abs(present - issueDate);
        long daysPassed = (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        if(daysPassed > validDays){
            long fineAmount = (daysPassed-validDays)*fineAmt;
            return securityAmount- (int) fineAmount;
        }
        return securityAmount;
    }
}

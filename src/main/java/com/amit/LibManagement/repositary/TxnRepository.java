package com.amit.LibManagement.repositary;

import com.amit.LibManagement.model.Txn;
import com.amit.LibManagement.model.TxnStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn, Integer> {
    Txn getUserPhoneNoAndBookBookNoAndTxnStatus(String phoneNo, String bookNo, TxnStatus issued);
}

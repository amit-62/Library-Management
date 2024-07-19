package com.amit.LibManagement.repositary;

import com.amit.LibManagement.model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn, Integer> {
}

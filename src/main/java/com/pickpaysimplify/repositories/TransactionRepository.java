package com.pickpaysimplify.repositories;

import com.pickpaysimplify.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

package com.pickpaysimplify.controllers;

import com.pickpaysimplify.domain.Transaction;
import com.pickpaysimplify.dto.TransactionDTO;
import com.pickpaysimplify.repositories.TransactionRepository;
import com.pickpaysimplify.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService service;

    @Autowired
    TransactionRepository repository;

    @PostMapping
    public ResponseEntity<?> newTransation(@RequestBody  TransactionDTO transaction) throws Exception {
        service.createNewTransaction(transaction);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = repository.findAll();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}

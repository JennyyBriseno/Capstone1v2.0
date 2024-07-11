package com.example.Capstone1v2.controllers;

import com.example.Capstone1v2.models.Transactions;
import com.example.Capstone1v2.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Transactions")
public class TransactionController {
    @Autowired TransactionService transactionService;

    @GetMapping
    ResponseEntity<List<Transactions>> getTransactions(){
        var transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable Long id){
        return new ResponseEntity<>(transactionService.displayById(id), HttpStatus.OK);
    }

    @GetMapping("/deposits")
    public ResponseEntity<Transactions> getDeposits(){
        return new ResponseEntity<>(transactionService.displayAllDeposits());
    }

    @GetMapping("/payments")
    public ResponseEntity<Transactions> getPayments(){
        return new ResponseEntity<>(transactionService.displayAllPayments());
    }

    @GetMapping("/vendor")
    public ResponseEntity<Transactions> getByVendor(@PathVariable String vendor){
        return new ResponseEntity<>(transactionService.displayByVendor(vendor));
    }

}

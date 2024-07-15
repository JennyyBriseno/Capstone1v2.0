package com.example.Capstone1v2.controllers;

import com.example.Capstone1v2.models.TransactionSearchParams;
import com.example.Capstone1v2.models.Transactions;
import com.example.Capstone1v2.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Transactions>> getDeposits(){
        return new ResponseEntity<>(transactionService.displayAllDeposits(), HttpStatus.OK);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Transactions>> getPayments(){
        return new ResponseEntity<>(transactionService.displayAllPayments(), HttpStatus.OK);
    }

    @GetMapping("/vendor/{vendor}")
    public ResponseEntity<List<Transactions>> getByVendor(@PathVariable String vendor){
        return new ResponseEntity<>(transactionService.displayByVendor(vendor), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Transactions>> getByCustomSearch(@ModelAttribute TransactionSearchParams searchParams){
        return new ResponseEntity<>(transactionService.searchTransactions(searchParams), HttpStatus.OK);
    }

    @GetMapping("/MonthToDate")
    public ResponseEntity<List<Transactions>> getMonthToDateTransactions(){
        return new ResponseEntity<>(transactionService.displayMonthToDate(), HttpStatus.OK);
    }

    @GetMapping("/PreviousMonth")
    public ResponseEntity<List<Transactions>> getPreviousMonthTransactions(){
        return new ResponseEntity<>(transactionService.displayPreviousMonth(), HttpStatus.OK);
    }

    @GetMapping("/YearToDate")
    public ResponseEntity<List<Transactions>> getYearToDateTransactions(){
        return new ResponseEntity<>(transactionService.displayYearToDate(), HttpStatus.OK);
    }

    @GetMapping("/PreviousYear")
    public ResponseEntity<List<Transactions>> getPreviousYearTransactions(){
        return new ResponseEntity<>(transactionService.displayPreviousYear(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@Valid @RequestBody Transactions transactions){
        return new ResponseEntity<>(transactionService.createTransaction(transactions), HttpStatus.OK);
    }

}

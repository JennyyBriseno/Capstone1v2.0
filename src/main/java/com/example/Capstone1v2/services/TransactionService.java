package com.example.Capstone1v2.services;

import com.example.Capstone1v2.exceptions.ResourceNotFoundException;
import com.example.Capstone1v2.models.TransactionSearchParams;
import com.example.Capstone1v2.models.TransactionSpecification;
import com.example.Capstone1v2.models.Transactions;
import org.springframework.data.jpa.domain.Specification;
import com.example.Capstone1v2.repositories.TransactionRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transactions> getAllTransactions(){
        List<Transactions> transactions = transactionRepository.findAll();
        return transactions;
    }

    public Transactions displayById(Long id){
        Transactions transactions = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction was not found, try again!" + id));

        return transactions;
    }

    public Transactions createTransaction(Transactions createTransaction){
        Transactions createdTransaction = createTransaction;
        Transactions savedTransaction = transactionRepository.save(createdTransaction);

        return savedTransaction;
    }

    public List<Transactions> displayMonthToDate(){
        throw new UnsupportedOperationException();
    }

    public List<Transactions> displayPreviousMonth(){
        throw new UnsupportedOperationException();
    }

    public List<Transactions> displayYearToDate(){
        throw new UnsupportedOperationException();
    }

    public List<Transactions> displayPreviousYear(){
        throw new UnsupportedOperationException();
    }

    public List<Transactions> displayAllDeposits(){
        throw new UnsupportedOperationException();
    }

    public List<Transactions> displayByVendor(){
        throw new UnsupportedOperationException();
    }

    public List<Transactions> displayAllPayments(){
        throw new UnsupportedOperationException();
    }

    //custom search
    public List<Transactions> searchTransactions(TransactionSearchParams tsp){
        Specification<Transactions> spec = where(TransactionSpecification.dateBetween(tsp.getStartDate(), tsp.getEndDate()))
                .and(TransactionSpecification.descriptionContains(tsp.getDescription()))
                .and(TransactionSpecification.vendorContains(tsp.getVendor()))
                .and(TransactionSpecification.amountBetween(tsp.getMinAmount(), tsp.getMaxAmount()));
        return transactionRepository.findAll(spec);
    }
}

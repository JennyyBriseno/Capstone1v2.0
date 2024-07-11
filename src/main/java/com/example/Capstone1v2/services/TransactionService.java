package com.example.Capstone1v2.services;

import com.example.Capstone1v2.exceptions.ResourceNotFoundException;
import com.example.Capstone1v2.models.TransactionSearchParams;
import com.example.Capstone1v2.models.TransactionSpecification;
import com.example.Capstone1v2.models.Transactions;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import com.example.Capstone1v2.repositories.TransactionRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
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
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfMonth = LocalDate.now().minusDays(currentDate.getDayOfMonth() - 1);

        List<Transactions> transactions = transactionRepository.findByDateBetween(startOfMonth, currentDate);

        return transactions;
    }

    public List<Transactions> displayPreviousMonth(){
        LocalDate currentDate = LocalDate.now();
        LocalDate lastMonthStartDate = LocalDate.now().minusMonths(1).minusDays(currentDate.getDayOfMonth() - 1);
        LocalDate endDate = LocalDate.now().minusDays(currentDate.getDayOfMonth() - 1);

        List<Transactions> transactions = transactionRepository.findByDateBetween(lastMonthStartDate, endDate);

        return transactions;
    }

    public List<Transactions> displayYearToDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfYear = currentDate.minusMonths(currentDate.getMonthValue() - 1).minusDays(currentDate.getDayOfMonth() - 1);

        List<Transactions> transactions = transactionRepository.findByDateBetween(startOfYear, currentDate);

        return transactions;
    }

    public List<Transactions> displayPreviousYear(){
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfLastYear = currentDate.minusMonths(currentDate.getMonthValue() - 1).minusDays(currentDate.getDayOfMonth() -1).minusYears(1);
        LocalDate lastDayOfYear = currentDate.minusMonths(currentDate.getMonthValue()).minusDays(currentDate.getDayOfMonth());

        List<Transactions> transactions = transactionRepository.findByDateBetween(startOfLastYear, lastDayOfYear);

        return transactions;
    }

    public List<Transactions> displayAllDeposits(){
        List<Transactions> deposits = transactionRepository.findByAmountGreaterThanZero();

        return deposits;
    }

    public List<Transactions> displayByVendor(String vendorChosen){
        List<Transactions> vendor = transactionRepository.findByVendorIgnoreCase(vendorChosen);

        return vendor;
    }

    public List<Transactions> displayAllPayments(){
        List<Transactions> payments = transactionRepository.findByAmountLessThanZero();

        return payments;
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

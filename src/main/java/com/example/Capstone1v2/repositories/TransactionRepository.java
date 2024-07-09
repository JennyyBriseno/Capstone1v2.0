package com.example.Capstone1v2.repositories;

import com.example.Capstone1v2.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByDate(String date);

    List<Transactions> findByDescriptionIgnoreCase(String description);

    List<Transactions> findByVendorIgnoreCase(String vendor);

    List<Transactions> findByAmount(double amount);

}

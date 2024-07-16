package com.example.Capstone1v2.repositories;

import com.example.Capstone1v2.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long>, JpaSpecificationExecutor<Transactions> {

    List<Transactions> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Transactions> findByVendorIgnoreCase(String vendor);

    List<Transactions> findByAmountGreaterThan(BigDecimal zero);

    List<Transactions> findByAmountLessThan(BigDecimal zero);

}

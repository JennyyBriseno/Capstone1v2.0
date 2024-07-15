package com.example.Capstone1v2.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionSearchParams {
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String vendor;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
}

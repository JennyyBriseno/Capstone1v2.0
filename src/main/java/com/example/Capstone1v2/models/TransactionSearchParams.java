package com.example.Capstone1v2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSearchParams {
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String vendor;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
}

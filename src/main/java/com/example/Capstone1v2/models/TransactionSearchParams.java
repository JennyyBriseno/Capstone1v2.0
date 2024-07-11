package com.example.Capstone1v2.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionSearchParams {
    private String startDate;
    private String endDate;
    private String description;
    private String vendor;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
}

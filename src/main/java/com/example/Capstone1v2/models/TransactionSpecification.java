package com.example.Capstone1v2.models;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class TransactionSpecification {
    public static Specification<Transactions> dateBetween(String start, String end) {
        return (root, query, criteriaBuilder) -> {
            if ((start == null || start.isEmpty()) && (end == null || end.isEmpty())) {
                return criteriaBuilder.conjunction();
            }
            if (start == null || start.isEmpty()) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("date"), end);
            }
            if (end == null || end.isEmpty()) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("date"), start);
            }
            return criteriaBuilder.between(root.get("date"), start, end);
        };
    }

    public static Specification<Transactions> descriptionContains(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("description"), "%" + text + "%");
        };
    }

    public static Specification<Transactions> vendorContains(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isEmpty()) {
              return criteriaBuilder.conjunction();
            }
          return criteriaBuilder.like(root.get("vendor"), "%" + text + "%");
        };
    }

    public static Specification<Transactions> amountBetween(BigDecimal min, BigDecimal max) {
        return (root, query, criteriaBuilder) -> {
            if (min == null && max == null) {
                return criteriaBuilder.conjunction();
            }
            if (min == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("amount"), max);
            }
            if (max == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), min);
            }
            return criteriaBuilder.between(root.get("amount"), min, max);
        };
    }
}

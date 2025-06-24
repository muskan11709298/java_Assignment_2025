package com.rewards.rewards_points_service.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private String customerId;
    private double amount;
    private String date;

    public Transaction(String customerId, double amount, String date) {
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public LocalDate getTransactionDate() {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}



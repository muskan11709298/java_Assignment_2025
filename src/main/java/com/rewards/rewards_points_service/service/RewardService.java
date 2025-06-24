package com.rewards.rewards_points_service.service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rewards.rewards_points_service.exception.InvalidTransactionException;
import com.rewards.rewards_points_service.model.RewardResponse;
import com.rewards.rewards_points_service.model.Transaction;

/**
 * Service class containing the core business logic to calculate rewards.
 */
@Service
public class RewardService {

    private static final List<Transaction> transactions = List.of(
            new Transaction("cust1", 120, "2025-01-15"),
            new Transaction("cust1", 80, "2025-02-10"),
            new Transaction("cust1", 200, "2025-03-05"),
            new Transaction("cust2", 40, "2025-01-17"),
            new Transaction("cust2", 130, "2025-02-22"),
            new Transaction("cust2", 90, "2025-03-01")
    );

    /**
     * Calculates rewards for all customers per month and in total.
     * @return list of reward responses
     */
    public List<RewardResponse> calculateAllRewards() {
        Map<String, Map<String, Integer>> customerRewards = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) 
            {
                throw new InvalidTransactionException("Transaction amount cannot be negative: " + transaction.getAmount());

            }
            
            String customerId = transaction.getCustomerId();
            Month month = transaction.getTransactionDate().getMonth();
            String monthName = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            int points = calculatePoints(transaction.getAmount());
            customerRewards.computeIfAbsent(customerId, k -> new HashMap<>())
                    .merge(monthName, points, Integer::sum);
        }

        return customerRewards.entrySet().stream()
                .map(entry -> {
                    Map<String, Integer> monthly = entry.getValue();
                    int total = monthly.values().stream().mapToInt(Integer::intValue).sum();
                    return new RewardResponse(entry.getKey(), monthly, total);
                })
                .collect(Collectors.toList());
    }

    private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += 2 * ((int) amount - 100);
            points += 50; // for $50-$100
        } else if (amount > 50) {
            points += (int) amount - 50;
        }
        return points;
    }
}


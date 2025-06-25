package com.rewards.rewards_points_service.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rewards.rewards_points_service.exception.InvalidTransactionException;
import com.rewards.rewards_points_service.model.RewardResponse;
import com.rewards.rewards_points_service.model.Transaction;
/**
 * Integration test for RewardService.
 *
 * It checks correct reward calculations, error handling, and transaction validation.
 */

@SpringBootTest
public class TestRewardServiceIntegrationTesting {

    @Autowired
    private RewardService rewardService;

    /**
     * Verifies that reward points are correctly calculated for all customers.
     * Ensures that "cust1" exists and has valid rewards for at least January.
     */
    @Test
    void testCalculateAllRewards() {
        List<RewardResponse> rewards = rewardService.calculateAllRewards();
        assertFalse(rewards.isEmpty());

        RewardResponse cust1 = rewards.stream()
            .filter(r -> r.getCustomerId().equals("cust1"))
            .findFirst()
            .orElse(null);

        assertNotNull(cust1);
        assertTrue(cust1.getTotalRewards() > 0);
        assertTrue(cust1.getMonthlyRewards().containsKey("January"));
    }

    /**
     * Ensures that trying to add a transaction with a negative amount
     * triggers a custom InvalidTransactionException.
     */
    @Test
    void testAddTransaction_invalidAmount() {
        Transaction transaction = new Transaction("cust5", -100.0, "2025-03-10");

        assertThrows(InvalidTransactionException.class, () -> {
            rewardService.addTransaction(transaction);
        });
    }
}

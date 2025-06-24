package com.rewards.rewards_points_service.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

public class RewardResponse {
    private String customerId;
    private Map<String, Integer> monthlyRewards;
    private int totalRewards;

    public RewardResponse(String customerId, Map<String, Integer> monthlyRewards, int totalRewards) {
        this.customerId = customerId;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }

    public int getTotalRewards() {
        return totalRewards;
    }
}

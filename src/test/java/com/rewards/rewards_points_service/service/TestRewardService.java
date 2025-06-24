package com.rewards.rewards_points_service.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.rewards.rewards_points_service.model.RewardResponse;

public class TestRewardService {
	
    private final RewardService service = new RewardService();

    @Test
    void testCalculateRewards() {
        List<RewardResponse> results = service.calculateAllRewards();
        assertFalse(results.isEmpty());
        assertTrue(results.stream().anyMatch(r -> r.getCustomerId().equals("cust1")));
    }

}

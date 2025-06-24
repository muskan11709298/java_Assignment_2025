package com.rewards.rewards_points_service.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rewards.rewards_points_service.model.RewardResponse;

public class TestRewardController {
	
    @Autowired
    private RewardController controller;

    @Test
    void testGetRewards() {
        List<RewardResponse> responses = controller.getAllRewards();
        assertNotNull(responses);
        assertTrue(responses.size() > 0);
    }


}

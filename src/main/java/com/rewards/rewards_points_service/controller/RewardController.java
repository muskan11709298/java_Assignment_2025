package com.rewards.rewards_points_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.rewards_points_service.model.RewardResponse;
import com.rewards.rewards_points_service.service.RewardService;

//REST controller to handle reward calculation requests.

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
	
    @Autowired
    private RewardService rewardService;
    
	/*
	 * end point to calculate rewards point for all customer
	 */    
    @GetMapping
    public List<RewardResponse> getAllRewards() {
        return rewardService.calculateAllRewards();
    }


	

}

package com.rewards.rewards_points_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.rewards_points_service.model.RewardResponse;
import com.rewards.rewards_points_service.model.Transaction;
import com.rewards.rewards_points_service.service.RewardService;

//REST controller to handle reward calculation requests.

@RestController
@RequestMapping("/api/rewards")
public class RewardController{
	@Autowired
	private RewardService rewardService;
	/*
	 * end point to calculate rewards point for all customer
	 */
	@GetMapping
	public List<RewardResponse> getAllRewards() {
		return rewardService.calculateAllRewards();
	}
	/*
	 * end point to calcuate reward point for customer based on customer id
	 */
	@GetMapping("/{customerId}")
	public ResponseEntity<RewardResponse> getRewardsByCustomer(@PathVariable String customerId) {
		return ResponseEntity.ok(rewardService.getRewardsByCustomerId(customerId));
	}
	/*
	 * Adds a new transaction for rewards calculation
	 */
	@PostMapping("/add-transaction")
	public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
		rewardService.addTransaction(transaction);
		return ResponseEntity.ok("Transaction added successfully");
	}
}

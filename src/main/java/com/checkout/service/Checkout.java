package com.checkout.service;

import java.util.HashMap;
import java.util.Map;

public class Checkout {

	// add methods to scan items, apply pricing-rules and find total price

	private Map<String, Integer> itemCountMap = new HashMap<String, Integer>();
	private PricingRules pricingRules;

	public void scan(String itemCode) {

	}

	public double findTotalPrice() {

		// apply pricing rules and find total

		return 0;

	}
}

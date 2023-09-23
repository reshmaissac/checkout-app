package com.checkout.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.checkout.exception.PricingRuleNotFoundException;
import com.checkout.offer.ISpecialOffer;

public class Checkout {

	private static final Logger logger = Logger.getLogger(Checkout.class.getName());
	private Map<Character, Integer> itemCountMap = new HashMap<Character, Integer>();
	private List<PricingRules> pricingRules;

	public Checkout(List<PricingRules> pricingRules) {
		this.pricingRules = pricingRules;
	}

	/**
	 * method to scan items.
	 * 
	 * @param itemCode
	 */
	public void scan(char itemCode) {

		try {
			if (findPricingRuleByItemCode(itemCode) != null) {

				itemCountMap.put(itemCode, itemCountMap.getOrDefault(itemCode, 0) + 1);
			}
		} catch (PricingRuleNotFoundException e) {

			logger.log(Level.WARNING, e.getMessage());
		}

	}

	/**
	 * method to sum all itemprices.
	 * 
	 * @return
	 */
	public double findTotalPrice() {
		return itemCountMap.entrySet().stream()
				.mapToDouble(itemEntry -> findItemTotalPrice(itemEntry.getKey(), itemEntry.getValue())).sum();
	}

	/**
	 * method to apply pricing rules and find total of each item.
	 * 
	 * @param itemCode
	 * @param itemCount
	 * @return
	 */
	public double findItemTotalPrice(char itemCode, int itemCount) {

		try {
			PricingRules pricingRule = findPricingRuleByItemCode(itemCode);
			// get unit-price of the item
			double unitPrice = pricingRule.getItem().getUnitPrice();
			// get special offer
			ISpecialOffer specialOffer = pricingRule.getSpecialOffer();
			if (specialOffer != null) {

				return specialOffer.applyOffer(itemCount, unitPrice);
			} else {
				return itemCount * unitPrice;
			}
		} catch (PricingRuleNotFoundException e) {

			logger.log(Level.WARNING, e.getMessage());

		}

		return 0;

	}

	private PricingRules findPricingRuleByItemCode(char itemCode) throws PricingRuleNotFoundException {
		// return scanned item
		return pricingRules.stream().filter(offer -> offer.getItem().getItemCode() == itemCode).findFirst()
				.orElseThrow(() -> new PricingRuleNotFoundException("Pricing rule not found for item: " + itemCode));

	}

	public Map<Character, Integer> getItemCountMap() {
		return itemCountMap;
	}

}

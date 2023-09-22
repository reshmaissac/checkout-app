package com.checkout.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.checkout.model.Item;
import com.checkout.service.offer.ISpecialOffer;

public class Checkout {

	// add methods to scan items, apply pricing-rules and find total price

	private Map<Character, Integer> itemCountMap = new HashMap<Character, Integer>();
	private List<PricingRules> pricingRules;
	private double totalPrice = 0;

	public Checkout(List<PricingRules> pricingRules) {
		this.pricingRules = pricingRules;
	}

	public void scan(char itemCode) {

		itemCountMap.put(itemCode, itemCountMap.getOrDefault(itemCode, 0) + 1);

	}

	public double findTotalPrice() {
		return itemCountMap.entrySet().stream()
				.mapToDouble(itemEntry -> findItemTotalPrice(itemEntry.getKey(), itemEntry.getValue())).sum();
		// return totalPrice;
	}

	public double findItemTotalPrice(char itemCode, int itemCount) {

		// itemCountMap
		// if (itemCountMap.containsKey(item.getItemCode())) {

		// int itemCount = itemCountMap.get(item.getItemCode());
		// get pricingRule of the item
		PricingRules pricingRule = findPricingRuleByItemCode(itemCode);
		if (pricingRule != null) {
			// get unit-price of the item
			double unitPrice = pricingRule.getItem().getUnitPrice();
			// get special offer
			ISpecialOffer specialOffer = pricingRule.getSpecialOffer();
			if (specialOffer != null) {

				return specialOffer.getItemDicsountedPrice(itemCount, unitPrice);
			} else {
				// double total = totalPrice + (itemCount * unitPrice);
				return itemCount * unitPrice;
			}
		} else {
            //logger.log(Level.WARNING, "Item not found in pricing rules: " + item);
        }
		// }

		// apply pricing rules and find total

		return 0;

	}

	private PricingRules findPricingRuleByItemCode(char itemCode) {
		// return scanned item
		return pricingRules.stream().filter(offer -> offer.getItem().getItemCode() == itemCode).findFirst()
				.orElse(null);

	}

	public Map<Character, Integer> getItemCountMap() {
		return itemCountMap;
	}

}

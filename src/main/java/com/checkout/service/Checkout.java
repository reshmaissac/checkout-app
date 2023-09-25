package com.checkout.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.checkout.exception.PricingRuleNotFoundException;
import com.checkout.model.Product;
import com.checkout.offer.ISpecialOffer;

public class Checkout {

	private static final Logger logger = Logger.getLogger(Checkout.class.getName());
	private Map<Character, Integer> itemCountMap = new HashMap<Character, Integer>();
	private List<PricingRules> pricingRules;
	private Stock stock;

	public Checkout(List<PricingRules> pricingRules, Stock stock) {
		this.stock = stock;
		this.pricingRules = pricingRules;
	}

	/**
	 * method to scan and add item to basket(hashmap).
	 * 
	 * @param itemCode
	 */
	public void scan(char itemCode) {

		if (stock.getProduct(itemCode).isPresent()) {

			itemCountMap.put(itemCode, itemCountMap.getOrDefault(itemCode, 0) + 1);

		} else {
			logger.log(Level.WARNING, "Invalid Item");
			
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

			// get unit-price of the item
			Optional<Product> product = stock.getProduct(itemCode);
			if (product.isPresent()) {

				double unitPrice = product.get().getUnitPrice();
				// get special offer rules for the item
				PricingRules pricingRule = findPricingRuleByItemCode(itemCode);
				ISpecialOffer specialOffer = pricingRule.getSpecialOffer();
				if (specialOffer != null) {

					return specialOffer.applyOffer(itemCount, unitPrice);
				} else {
					return itemCount * unitPrice;
				}
			}
		} catch (PricingRuleNotFoundException e) {

			logger.log(Level.WARNING, e.getMessage());

		}

		return 0;

	}

	/**
	 * method to find offer rules associated with the scanned item.
	 * 
	 * @param itemCode
	 * @return
	 * @throws PricingRuleNotFoundException
	 */
	private PricingRules findPricingRuleByItemCode(char itemCode) throws PricingRuleNotFoundException {
		return pricingRules.stream().filter(offer -> offer.getItemCode() == itemCode).findFirst()
				.orElseThrow(() -> new PricingRuleNotFoundException("Pricing rule not found for item: " + itemCode));

	}

	public Map<Character, Integer> getItemCountMap() {
		return itemCountMap;
	}

}

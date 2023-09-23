package com.checkout.util;

import java.util.Arrays;
import java.util.List;
import com.checkout.model.Item;
import com.checkout.offer.BuyNforPrice;
import com.checkout.service.Checkout;
import com.checkout.service.PricingRules;

public class AppUtil {

	public static List<PricingRules> loadPricingRules() {
		
		List<PricingRules> pricingRules = Arrays.asList(
				new PricingRules(new Item('A', 50), new BuyNforPrice(3, 130d)),
				new PricingRules(new Item('B', 30), new BuyNforPrice(2, 45d)),
				new PricingRules(new Item('C', 20), null), 
				new PricingRules(new Item('D', 15), null));
		return pricingRules;
	}

	public static boolean isValidItem(char scannedItemCode) {
		if (Character.isLetter(scannedItemCode)) {
			return true;
		}
		return false;
	}

	public static void printBill(Checkout checkout) {
		System.out.println("Item: " + "  Qty: " + "  Price: ");

		checkout.getItemCountMap().entrySet().forEach(item -> {

			System.out.println(item.getKey() + "       " + item.getValue() + "       "
					+ checkout.findItemTotalPrice(item.getKey(), item.getValue()));

		});
		System.out.println("-------------------------------------");

		// print total price
		double totalPrice = checkout.findTotalPrice();
		System.out.println("Total(pence) = " + totalPrice);
		System.out.println();
	}

	public static void errorMessage(String error) {
		System.out.println(error);
		System.out.println();

	}

}

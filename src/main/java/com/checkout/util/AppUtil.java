package com.checkout.util;

import java.util.Arrays;
import java.util.List;

import com.checkout.model.Product;
import com.checkout.offer.BuyNforPrice;
import com.checkout.service.Checkout;
import com.checkout.service.PricingRules;
import com.checkout.service.Stock;

public class AppUtil {
	
	/**
	 * add products to stock.
	 * @return
	 */
	public static Stock addProductsToStore() {
		Stock stock = new Stock();
		
		List<Product> products = Arrays.asList(
				new Product('A', 50), 
				new Product('B', 30), 
				new Product('C', 20),
				new Product('D', 15));
		
		stock.addProducts(products);
		
		return stock;
	}

	/**
	 * fetch pricing rules at checkout. 
	 * @return
	 */
	public static List<PricingRules> loadPricingRules() {

		List<PricingRules> pricingRules = Arrays.asList(
				new PricingRules('A', new BuyNforPrice(3, 130d)),
				new PricingRules('B', new BuyNforPrice(2, 45d)), 
				new PricingRules('C', null),
				new PricingRules('D', null));

		return pricingRules;
	}

	/**
	 * check if the input is valid (A, B, C, etc.).
	 * @param scannedItemCode
	 * @return
	 */
	public static boolean isValidItem(char scannedItemCode) {
		if (Character.isLetter(scannedItemCode)) {
			return true;
		}
		return false;
	}

	/**
	 * method to print bill at checkout.
	 * @param checkout
	 */
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

	/**
	 * method to print error messages.
	 * @param error
	 */
	public static void errorMessage(String error) {
		System.out.println(error);
		System.out.println();

	}

}

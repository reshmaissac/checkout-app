package com.checkout;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.checkout.model.Item;
import com.checkout.service.Checkout;
import com.checkout.service.PricingRules;
import com.checkout.service.offer.BuyNforPrice;

public class AppMain {

	public static void main(String[] args) {

		// load product pricing and offers
		List<PricingRules> pricingRules = Arrays.asList(
				new PricingRules(new Item('A', 50), new BuyNforPrice(3, 130d)),
				new PricingRules(new Item('B', 30), new BuyNforPrice(2, 45d)),
				new PricingRules(new Item('C', 20), null), 
				new PricingRules(new Item('D', 15), null));
		Checkout checkout = new Checkout(pricingRules);

		Scanner scanner = new Scanner(System.in);
		while (true) {

			System.out.println("Please enter item code (A,B,C,D) or 'exit' to finish : ");
			String input = scanner.nextLine().toUpperCase();

			// Check if the input is valid (A, B, C, etc.)
			if (input.equalsIgnoreCase("EXIT")) {

				break;

			} else {
				char scannedItemCode = input.charAt(0);
				if (Character.isLetter(scannedItemCode)) {

					// scan item
					checkout.scan(scannedItemCode);
					System.out.println("Item " + scannedItemCode + " , Qty : "
							+ checkout.getItemCountMap().getOrDefault(scannedItemCode, 0));

					// print running price
					double totalPrice = checkout.findTotalPrice();
					System.out.println("Total : " + totalPrice + " pence");

				} else {

					System.out.println("Invalid item code. Please enter a valid item code.");

				}
			}

		}

		scanner.close();

	}

}

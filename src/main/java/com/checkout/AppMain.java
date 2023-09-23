package com.checkout;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.checkout.service.Checkout;
import com.checkout.service.PricingRules;
import com.checkout.util.AppUtil;

public class AppMain {

	private static final Logger logger = Logger.getLogger(AppMain.class.getName());

	public static void main(String[] args) {

		List<PricingRules> pricingRules = AppUtil.loadPricingRules(); // load product pricing rules
		Checkout checkout = new Checkout(pricingRules);

		Scanner scanner = new Scanner(System.in);
		while (true) {

			System.out.println("Please enter item code (A,B,C,D) or 'exit' to finish : ");
			String input = scanner.nextLine().toUpperCase();

			if (input.equalsIgnoreCase("EXIT")) {
				
				AppUtil.printBill(checkout); // print price details
				break;

			} else {

				char scannedItemCode = input.charAt(0);
				// Check if the input is valid (A, B, C, etc.)
				if (AppUtil.isValidItem(scannedItemCode)) {

					checkout.scan(scannedItemCode); // scan item

					AppUtil.printBill(checkout); // print price details

				} else {

					AppUtil.errorMessage("Invalid item code. Please enter a valid item code.");
					logger.log(Level.WARNING, "Invalid Item Code");

				}
			}

		}

		scanner.close();

	}

}

package com.checkout;

import java.util.Scanner;

public class AppMain {

	public static void main(String[] args) {

		// load products and offers

		// methods to get the command line inputs and display output
		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("Please enter item code (A,B,C,..) or 'exit' to finish : ");
			String input = scanner.nextLine().toUpperCase();

			// Check if the input is valid (A, B, C, etc.)
			if (input.equalsIgnoreCase("EXIT")) {
				
				break;
				
			} else if (Character.isLetter(input.charAt(0))) {

				// scan item and print running price

			} else {
				
				System.out.println("Invalid item code. Please enter a valid item code.");

			}

		}

		scanner.close();

	}

}

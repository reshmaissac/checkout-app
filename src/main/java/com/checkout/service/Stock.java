package com.checkout.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.checkout.model.Product;

public class Stock {

	private HashMap<Character, Product> productMap;

	public Stock() {
		productMap = new HashMap<Character, Product>();
	}

	/**
	 * method to add products to store inventory list.
	 * @param products
	 */
	public void addProducts(List<Product> products) {
		productMap.clear();
		products.forEach(product -> {
			productMap.put(product.getItemCode(), product);
		});
	}

	/**
	 * method to lookup for products in the stock.
	 * @param itemCode
	 * @return
	 */
	public Optional<Product> getProduct(char itemCode) {
		Optional<Product> product = Optional.ofNullable(productMap.getOrDefault(itemCode, null));
		return product;

	}

}

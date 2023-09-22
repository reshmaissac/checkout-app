package com.checkout.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.checkout.model.Item;
import com.checkout.service.offer.BuyNforPrice;

public class CheckoutTest {
	Checkout checkout;
	private List<PricingRules> pricingRules;

	@Before
	public void setUp() {
		pricingRules = new ArrayList<>();
		pricingRules.addAll(Arrays.asList(new PricingRules(
				new Item('A', 50), new BuyNforPrice(3, 130d)),
				new PricingRules(new Item('B', 30), new BuyNforPrice(2, 45d)),
				new PricingRules(new Item('C', 20), null), 
				new PricingRules(new Item('D', 15), null)));

		checkout = new Checkout(pricingRules);

	}

	@Test
	public void testScanMultipleItems() {
		checkout.scan('A');
		checkout.scan('B');
		double total = checkout.findTotalPrice();
		assertEquals(80, total, 0.01);
	}

	@Test
	public void testScanItemsWithOffer() {
		checkout.scan('B');
		checkout.scan('B');

		double total = checkout.findTotalPrice();
		assertEquals(45, total, 0.01);
	}

	@Test
	public void testScanMultipleItemsWithOffer() {
		checkout.scan('A');
		checkout.scan('A');
		checkout.scan('A');
		checkout.scan('B');
		checkout.scan('B');

		double total = checkout.findTotalPrice();
		assertEquals(175, total, 0.01);
	}

	@Test
	public void testScanItemsWithandWithoutOffer() {
		checkout.scan('A');
		checkout.scan('A');
		checkout.scan('A');
		checkout.scan('B');
		checkout.scan('B');
		checkout.scan('C');
		checkout.scan('C');

		double total = checkout.findTotalPrice();
		assertEquals(215, total, 0.01);
	}

	@Test
	public void testScanItemWithNoOffer() {
		checkout.scan('C');
		double total = checkout.findTotalPrice();
		assertEquals(20, total, 0.01);
	}

	@Test
	public void testScanMultipleItemsWithNoOffer() {
		checkout.scan('C');
		checkout.scan('C');
		checkout.scan('D');
		double total = checkout.findTotalPrice();
		assertEquals(55, total, 0.01);
	}

	@Test
	public void testScanItemsNotInPricingRules() {

		checkout.scan('E');
		double total = checkout.findTotalPrice();
		assertEquals(0, total, 0.01);
	}

	@Test
	public void testScanInvalidItemCode() {
		checkout.scan('1');
		double total = checkout.findTotalPrice();
		assertEquals(0, total, 0.01); // Invalid input for item code
	}

	@Test
	public void testFindTotalWithNoItemCode() {

		double total = checkout.findTotalPrice();
		assertEquals(0, total, 0.01); // Invalid input for item code
	}

}

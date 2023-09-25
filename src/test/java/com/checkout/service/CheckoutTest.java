package com.checkout.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.checkout.util.AppUtil;

public class CheckoutTest {

	Checkout checkout;

	@Before
	public void setUp() {
		
		Stock stock = AppUtil.addProductsToStore();
		List<PricingRules> pricingRules = AppUtil.loadPricingRules();

		checkout = new Checkout(pricingRules, stock);

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
		checkout.scan('1'); // Invalid input for item code
		double total = checkout.findTotalPrice();
		assertEquals(0, total, 0.01);
	}

	@Test
	public void testFindTotalWithNoItemCode() {

		double total = checkout.findTotalPrice();
		assertEquals(0, total, 0.01);
	}

}

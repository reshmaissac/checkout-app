package com.checkout.service.offer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class BuyNforPriceTest {

	BuyNforPrice buy2for45 = new BuyNforPrice(2, 45d);
	BuyNforPrice buy3for130 = new BuyNforPrice(3, 130d);

	@Test
	public void testBuy2for45() {

		assertEquals(45, buy2for45.getItemDicsountedPrice(2, 50).doubleValue(), 0.001);

	}

	@Test
	public void testBuy2for45RedeemTwice() {

		assertEquals(90, buy2for45.getItemDicsountedPrice(4, 50), 0.01);

	}

	@Test
	public void testBuy2for45With3Items() {

		assertEquals(95, buy2for45.getItemDicsountedPrice(3, 50), 0.01);
	}

	@Test
	public void testBuyNforPriceWithNoItem() {

		assertEquals(0, buy3for130.getItemDicsountedPrice(0, 0), 0.001);
	}

	@Test
	public void testBuy2for45NotEqual() {

		assertNotEquals(50, buy2for45.getItemDicsountedPrice(2, 50).doubleValue(), 0.001);
	}

	@Test
	public void testBuy3for130() {

		assertEquals(130, buy3for130.getItemDicsountedPrice(3, 30).doubleValue(), 0.001);

	}

	@Test
	public void testBuyNforPriceNoOffer() {

		BuyNforPrice buyNforPrice = new BuyNforPrice(1, 0d);

		// No discount, hence discountedPrice = 0
		assertEquals(0, buyNforPrice.getItemDicsountedPrice(2, 30).doubleValue(), 0.001);

	}

}

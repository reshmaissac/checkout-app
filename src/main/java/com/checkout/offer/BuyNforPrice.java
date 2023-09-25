package com.checkout.offer;

public class BuyNforPrice implements ISpecialOffer {

	private Integer offerQuantity;
	private Double offerPrice;

	public BuyNforPrice(Integer offerQty, Double offerPrice) {
		this.offerQuantity = offerQty;
		this.offerPrice = offerPrice;

	}

	/**
	 * method to get the discounted price of item after applying offer rule.
	 */
	@Override
	public Double applyOffer(int qty, double unitPrice) {

		return qty / offerQuantity * offerPrice + qty % offerQuantity * unitPrice;

	}

}

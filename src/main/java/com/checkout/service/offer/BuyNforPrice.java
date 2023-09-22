package com.checkout.service.offer;

public class BuyNforPrice implements ISpecialOffer {

	private Integer offerQuantity;
	private Double offerPrice;

	public BuyNforPrice(Integer offerQty, Double offerPrice) {
		this.offerQuantity = offerQty;
		this.offerPrice = offerPrice;

	}

	@Override
	public Double getItemDicsountedPrice(int qty, double unitPrice) {

		return qty / offerQuantity * offerPrice + qty % offerQuantity * unitPrice;

	}

}

package com.checkout.service.offer;

public class BuyNforPrice implements ISpecialOffer {

	private Integer offerQuantity;
	private Double offerPrice;

	public BuyNforPrice(Integer offerQty, Double offerPrice) {
		this.offerQuantity = offerQty;
		this.offerPrice = offerPrice;

	}

	@Override
	public Double findDicsountedPrice() {
		// TODO Auto-generated method stub
		return null;
	}

}

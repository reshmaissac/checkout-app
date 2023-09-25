package com.checkout.service;

import com.checkout.offer.ISpecialOffer;

public class PricingRules {

	private char itemCode;
	private ISpecialOffer specialOffer;

	public PricingRules(char itemCode, ISpecialOffer specialOffer) {
		this.itemCode = itemCode;
		this.specialOffer = specialOffer;
	}

	public char getItemCode() {
		return itemCode;
	}

	public void setItemCode(char itemCode) {
		this.itemCode = itemCode;
	}

	public ISpecialOffer getSpecialOffer() {
		return specialOffer;
	}

	public void setSpecialOffer(ISpecialOffer specialOffer) {
		this.specialOffer = specialOffer;
	}

}

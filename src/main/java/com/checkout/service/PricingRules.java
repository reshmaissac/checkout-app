package com.checkout.service;

import com.checkout.model.Item;
import com.checkout.service.offer.ISpecialOffer;

public class PricingRules {

	private Item item;
	private ISpecialOffer specialOffer;

	public PricingRules(Item item, ISpecialOffer specialOffer) {
		this.item = item;
		this.specialOffer = specialOffer;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ISpecialOffer getSpecialOffer() {
		return specialOffer;
	}

	public void setSpecialOffer(ISpecialOffer specialOffer) {
		this.specialOffer = specialOffer;
	}

}

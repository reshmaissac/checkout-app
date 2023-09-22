package com.checkout.model;

public class Item {

	private char itemCode;
	private double unitPrice;
	
	public Item(char itemCode, double price) {
		this.itemCode = itemCode;
		this.unitPrice = price;
	}

	public char getItemCode() {
		return itemCode;
	}


	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	

}

package com.checkout.model;

public class Product {

	private char itemCode;
	private double unitPrice;
	
	public Product(char itemCode, double price) {
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

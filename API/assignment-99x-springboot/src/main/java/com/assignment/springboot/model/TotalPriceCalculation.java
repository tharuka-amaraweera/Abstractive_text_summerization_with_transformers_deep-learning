package com.assignment.springboot.model;

public class TotalPriceCalculation {
	private Product product;
	private Integer unitAmount;
	private Integer cartonAmount;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(Integer unitAmount) {
		this.unitAmount = unitAmount;
	}
	public Integer getCartonAmount() {
		return cartonAmount;
	}
	public void setCartonAmount(Integer cartonAmount) {
		this.cartonAmount = cartonAmount;
	}

}

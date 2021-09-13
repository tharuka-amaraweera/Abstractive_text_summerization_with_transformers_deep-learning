package com.assignment.springboot.model;

public class MultipleProducts {
	
	private Integer productId;
	private Integer unitAmount;
	private Integer cartonAmount;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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

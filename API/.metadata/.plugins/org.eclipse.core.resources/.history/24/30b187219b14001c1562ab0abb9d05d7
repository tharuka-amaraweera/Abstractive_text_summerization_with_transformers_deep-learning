package com.assignment.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.assignment.springboot.constants.Constants;

@Entity
@Table(name ="products_tab")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "units_per_carton")
	private int unitsPerCarton;
	
	@Column(name = "price_of_carton")
	private double priceOfCarton;
	
	@Column(name = "img_url")
	private String productImageURL;
	
	
	
	
	public Product(String productName, int unitsPerCarton, double priceOfCarton, String productImageURL) {
		super();
		this.productName = productName;
		this.unitsPerCarton = unitsPerCarton;
		this.priceOfCarton = priceOfCarton;
		this.productImageURL = productImageURL;
	}
	
	
	public Product() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getUnitsPerCarton() {
		return unitsPerCarton;
	}
	public void setUnitsPerCarton(int unitsPerCarton) {
		this.unitsPerCarton = unitsPerCarton;
	}
	public double getPriceOfCarton() {
		return priceOfCarton;
	}
	public void setPriceOfCarton(double priceOfCarton) {
		this.priceOfCarton = priceOfCarton;
	}
	public String getProductImageURL() {
		return productImageURL;
	}
	public void setProductImageURL(String productImageURL) {
		this.productImageURL = productImageURL;
	}
	
	public  Double calculatePricePerSingleUnit() {
		return ((this.priceOfCarton/this.unitsPerCarton)+(this.priceOfCarton/this.unitsPerCarton* Constants.INCREASEDPERCENTAGEFORSINGLEUNIT));
	}
	


}

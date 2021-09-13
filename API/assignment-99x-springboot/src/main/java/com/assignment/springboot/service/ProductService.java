package com.assignment.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.springboot.constants.Constants;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.model.Product;
import com.assignment.springboot.model.TotalPriceCalculation;
import com.assignment.springboot.repository.ProductRepository;


@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	//get all products available
	public List<Product> getAllProducts(){
		return this.productRepository.findAll();
	}
	
	//get product by id
	public Product getProductById(long id) throws  ResourceNotFoundException{
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("A product is not available for this id :: "+id));
		return product;
	}
	
	//add products
	public Product addProduct(Product product) {
		return this.productRepository.save(product);
	}
	
	//calculate price for single product
	public Double calculatePricePerSingleProduct(Product product, Integer unitAmount, Integer cartonAmount ) {
		int noOfCartons = cartonAmount;
		int noOfUnits = unitAmount;
		double totalPrice = 0.0;

		if(unitAmount>0) {
			noOfCartons = noOfCartons + (unitAmount/product.getUnitsPerCarton());
			noOfUnits = unitAmount % product.getUnitsPerCarton();
		}
		
		double cartonPrice = noOfCartons * product.getPriceOfCarton();
		double singleUnitPrice = noOfUnits * product.calculatePricePerSingleUnit();
		totalPrice = cartonPrice + singleUnitPrice;
		
		if(noOfCartons>=Constants.MINIMUNAMOUNTFORDISCOUNT) {
			double discountAmount = product.getPriceOfCarton() * Constants.DISCOUNTPERCENTAGE;
			totalPrice = totalPrice - discountAmount;
		}
		System.out.println(totalPrice);
		return totalPrice;
		
		
		
		
	}
	
	//calculate total price for all purchased products
	public Double computeTotalPrice(List<TotalPriceCalculation> totalList) {
		Double finalPrice=0.0;
		for(TotalPriceCalculation pickedProduct: totalList) {
			finalPrice = finalPrice + this.calculatePricePerSingleProduct(pickedProduct.getProduct(), pickedProduct.getUnitAmount(), pickedProduct.getCartonAmount());
		}
		return finalPrice;
	}
	

}


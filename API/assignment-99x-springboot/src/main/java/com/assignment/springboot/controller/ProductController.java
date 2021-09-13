package com.assignment.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.model.MultipleProducts;
import com.assignment.springboot.model.Product;
import com.assignment.springboot.model.TotalPriceCalculation;
import com.assignment.springboot.service.ProductService;

@RestController
@RequestMapping("/api/")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//get all products
	@GetMapping("products")
	public ResponseEntity<List<Product>> getAllProducts(){
		List <Product> productList = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(productList, new HttpHeaders(), HttpStatus.OK );
	}
	
	//get product by id
	@GetMapping("product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ResourceNotFoundException{
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	//add products
	@PostMapping("products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product newProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(newProduct, new HttpHeaders(), HttpStatus.OK);
	}
	
	//calculate the price for single product purchase
	@GetMapping("/calculate_price/single/{id}/{unitAmount}/{cartonAmount}")
	public ResponseEntity<Double> calculatePricePerSingleProduct(@PathVariable("id") Long id, @PathVariable("unitAmount") Integer unitAmount,  @PathVariable("cartonAmount") Integer cartonAmount ) throws ResourceNotFoundException{
		Product product = productService.getProductById(id);
		Double finalAmount = 0.0;
		if(product!=null) {
			finalAmount = productService.calculatePricePerSingleProduct(product, unitAmount, cartonAmount);
		}
		return new ResponseEntity<Double>(finalAmount, new HttpHeaders(), HttpStatus.OK);
	}
	
	//calculate total prices for all product purchased
	@PostMapping("calculate_price/total")
	public ResponseEntity<Double>calculateTotalPriceForAllProducts(@RequestBody ArrayList<MultipleProducts> productsList)throws ResourceNotFoundException{
		Double finalAmount = 0.0;
		List<TotalPriceCalculation> products = new ArrayList<TotalPriceCalculation>();
		
		for(MultipleProducts product: productsList) {
			Product existingProduct = productService.getProductById(product.getProductId());
			if(existingProduct!= null) {
				TotalPriceCalculation newAmount = new TotalPriceCalculation();
				newAmount.setProduct(existingProduct);
				newAmount.setUnitAmount(product.getUnitAmount());
				newAmount.setCartonAmount(product.getCartonAmount());
				products.add(newAmount);
				
			}
		}
		finalAmount = productService.computeTotalPrice(products);
		return new ResponseEntity<Double>(finalAmount, new HttpHeaders(), HttpStatus.OK);
		
	}

}
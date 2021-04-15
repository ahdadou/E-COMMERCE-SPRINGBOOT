package com.shop.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.entity.Product;
import com.shop.demo.services.CategoryService;
import com.shop.demo.services.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/all")
public class allController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	//get All
		@GetMapping("/products")
		public ResponseEntity<?> getAll(){
			
			try {
				
				List<Product> product = productService.getall();
				return new ResponseEntity<>(product,HttpStatus.OK);
			}catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<>("Cant save category",HttpStatus.NOT_FOUND);
			}
			
		}
		
	// getById
		
		@GetMapping("product/id/{id}")
		public ResponseEntity<?> getById(@PathVariable("id")Long id){
			
			try {			
				Product product = productService.getProductById(id);
				return new ResponseEntity<>(product,HttpStatus.OK);
			}catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<>("Cant save category",HttpStatus.NOT_FOUND);
			}
			
		}

	// getByCategory
		
		@GetMapping("product/category/{category}")
		public ResponseEntity<?> getByCategory(@PathVariable("category") String category){
			
			try {
				List<Product> product = productService.getallByCategory(category);
				return new ResponseEntity<>(product,HttpStatus.OK);
			}catch (Exception e) {
				// TODO: handle exception
				return new ResponseEntity<>("Cant Find Products",HttpStatus.NOT_FOUND);
			}
			
		}
		

}

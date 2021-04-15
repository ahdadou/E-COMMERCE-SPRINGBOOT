package com.shop.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.entity.Category;
import com.shop.demo.entity.Country;
import com.shop.demo.entity.Product;
import com.shop.demo.models.request.CategoryForm;
import com.shop.demo.models.request.CountryForm;
import com.shop.demo.services.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/config/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveCountry(@RequestBody CategoryForm categoryForm){
		
		try {
			Category c = new Category();
			c.setName(categoryForm.getName());
			categoryService.save(c);
			return ResponseEntity.ok("Save Successfully");
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant save category",HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll(){
		
		try {
			
			List<Category> caterory = categoryService.getAll();
			return new ResponseEntity<>(caterory,HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant save category",HttpStatus.NOT_FOUND);
		}
		
	}

}

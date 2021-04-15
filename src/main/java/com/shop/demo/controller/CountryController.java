package com.shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.entity.Country;
import com.shop.demo.models.request.CountryForm;
import com.shop.demo.services.CountryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/config/country")
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> saveCountry(@RequestBody CountryForm countryForm){
		
		try {
			Country c = new Country();
			c.setName(countryForm.getName());
			countryService.save(c);
			return ResponseEntity.ok("Save Successfully");
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant save country",HttpStatus.NOT_FOUND);
		}
		
	}
		
	
}

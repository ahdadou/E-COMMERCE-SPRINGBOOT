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

import com.shop.demo.entity.City;
import com.shop.demo.entity.Country;
import com.shop.demo.models.request.CityForm;
import com.shop.demo.models.request.CountryForm;
import com.shop.demo.services.CityService;
import com.shop.demo.services.CountryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/config/city")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	@Autowired
	CountryService  countryservice;
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveCity(@RequestBody CityForm cityForm){
		
		try {
			City c = new City();
			Country ct = new Country();
			ct = countryservice.getByName(cityForm.getCountry());
			
			c.setName(cityForm.getName());
			c.setCountry(ct);			
			cityService.save(c);
			return ResponseEntity.ok("Save Successfully");
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant save country",HttpStatus.NOT_FOUND);
		}
		
	}

}

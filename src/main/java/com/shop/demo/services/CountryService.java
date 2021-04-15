package com.shop.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.demo.entity.Country;
import com.shop.demo.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	
	public Country save(Country c) {
		return countryRepository.save(c);
	}
	

//	By Name
	public Country getByName(String name) {
		Country country= countryRepository.findByName(name);
		return country;
		
	}
	
//	By Id
	public Country getById(Long id) {
		Country country= countryRepository.findById(id).get();
		return country;
		
	}
	 
// Get all
	 
	public List<Country> getAll() {
		 List<Country> country= countryRepository.findAll();
			return country;
			
		}
	 
	 
	 

}

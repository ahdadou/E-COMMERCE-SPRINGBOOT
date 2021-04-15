package com.shop.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.demo.entity.City;
import com.shop.demo.entity.Country;
import com.shop.demo.repository.CityRepository;



@Service
public class CityService {

	

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryService countryService;
	
	
	
//	Save
	
	public City save(City ct) {
		City cty = cityRepository.save(ct);
		return cty;
	}

//	By Name
	public City getByName(String name) {
		City City= cityRepository.findByName(name);
		return City;
		
	}
	
//	By Id
	public City getById(Long id) {
		City city= cityRepository.findById(id).get();
		return city;
		
	}
	 
// Get all
	 
	public List<City> getAll() {
		 List<City> city= cityRepository.findAll();
			return city;
			
		}
	
//	Get cities by Country id
	public List<City> getByCountryId(Long id) {
		 List<City> city= cityRepository.getByCountryId(id);
		return city;
			
		}
	
//	Get cities by Country id
	public List<City> getByCountryName(String name) {
		
		 Country c = countryService.getByName(name);
		 List<City> city= cityRepository.getByCountryId(c.getCountry_id());
		return city;
			
		}
	
	
	
	 
}

package com.shop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

	City findByName(String name);
	
	@Query(value = "SELECT n FROM city n WHERE n.country_id = :countryId",nativeQuery = true)
	List<City> getByCountryId(@Param("countryId") Long countryId);

}

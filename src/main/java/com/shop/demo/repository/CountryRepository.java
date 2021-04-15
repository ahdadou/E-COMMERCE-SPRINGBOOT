package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{

	Country findByName(String name);

}

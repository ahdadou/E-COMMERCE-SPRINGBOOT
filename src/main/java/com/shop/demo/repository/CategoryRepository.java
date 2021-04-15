package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findByName(String name);

	

}

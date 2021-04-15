package com.shop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findByName(String name);
	
	@Query(value = "SELECT Yn.* FROM product Yn WHERE Yn.category_id = :id",nativeQuery = true)
	List<Product> findByCategoryId(@Param("id") Long id);
	

}

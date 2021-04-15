package com.shop.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.demo.entity.Category;
import com.shop.demo.entity.Category;
import com.shop.demo.entity.Country;
import com.shop.demo.entity.Product;
import com.shop.demo.repository.CategoryRepository;
import com.shop.demo.repository.ProductRepository;
import com.shop.demo.repository.CategoryRepository;



@Service
public class ProductService {

	

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductRepository productRepository;
	
//	Save
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	
//	By Name
	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}
	
//	By Id
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}
	
// Get all By Category 
	public List<Product> getallByCategory(String category) {
		Category cat = categoryService.getByName(category);
		System.out.println(cat);
		return productRepository.findByCategoryId(cat.getCategory_id());
	}
		
// Get all
	public List<Product> getall() {	
		return productRepository.findAll();
	}
	
	
// Delete By id
	
   public void deleteProduct(Product p) {
	   productRepository.delete(p);
   }

	

	 
}

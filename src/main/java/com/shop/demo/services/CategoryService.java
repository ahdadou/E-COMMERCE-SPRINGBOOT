package com.shop.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.demo.entity.Category;
import com.shop.demo.entity.Category;
import com.shop.demo.entity.Country;
import com.shop.demo.repository.CategoryRepository;
import com.shop.demo.repository.CategoryRepository;



@Service
public class CategoryService {

	

	@Autowired
	private CategoryRepository categoryRepository;
	
//	Save
	
	public Category save(Category ct) {
		Category cty = categoryRepository.save(ct);
		return cty;
	}

//	By Name
	public Category getByName(String name) {
		Category Category= categoryRepository.findByName(name);
		return Category;
	}
	
//	By Id
	public Category getById(Long id) {
		Category Category= categoryRepository.findById(id).get();
		return Category;
		
	}
	 
// Get all
	 
	public List<Category> getAll() {
		 List<Category> Category= categoryRepository.findAll();
			return Category;
			
		}
	

	 
}

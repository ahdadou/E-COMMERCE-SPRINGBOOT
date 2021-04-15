package com.shop.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.shop.demo.entity.Category;
import com.shop.demo.entity.Product;
import com.shop.demo.models.request.CategoryForm;
import com.shop.demo.models.request.ProductForm;
import com.shop.demo.services.CategoryService;
import com.shop.demo.services.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/config/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	
//	Add
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveCountry(@RequestPart("productForm") String productJson, @RequestParam(value="files", required = false) MultipartFile file){
		
		Product product = new Product();
		System.out.println(productJson);
		try {
			ProductForm productForm= getJsonProduct(productJson);
			Category c = categoryService.getByName(productForm.getCategory());
			Date currentUtilDate = new Date();
			Product p = new Product();
			p.setName(productForm.getName());
			p.setShort_description(productForm.getShort_description());
			p.setLong_description(productForm.getLong_description());
			p.setUpdate_date(currentUtilDate);
			p.setPrice(productForm.getPrice());
			p.setQuantite(productForm.getQuantite());
			p.setCategory(c);
			
			 product = productService.saveProduct(p);
			
			 System.out.println("************************ SUCCES ADD *************** \n");
			
			if(file!=null) {							
				System.out.println("fileeeeeeeeeee");
				try {
					byte[] bytes=file.getBytes();
					String name=product.getProduct_id()+".png";
				
					
//				    Files.createDirectories(Paths.get("src/main/resources/static/images/products/"+product.getProduct_id()+"/"));
					BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/products/"+name)));

					stream.write(bytes);
					stream.close();
					product.setImg(name);
					
					productService.saveProduct(p);	
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		
				}
		   	
			
			return new ResponseEntity<>(product,HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant save category",HttpStatus.NOT_FOUND);
		}
		
	}

	
//delete
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
		
		try {
			  Product p = productService.getProductById(id);
			 productService.deleteProduct(p);
			return new ResponseEntity<>(p,HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant save category",HttpStatus.NOT_FOUND);
		}
		
	}
	
//get All
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll(){
		
		try {
			
			List<Product> product = productService.getall();
			return new ResponseEntity<>(product,HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant save category",HttpStatus.NOT_FOUND);
		}
		
	}
	
// getById
	
	@GetMapping("/id/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getById(@PathVariable("id")Long id){
		
		try {			
			Product product = productService.getProductById(id);
			return new ResponseEntity<>(product,HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant save category",HttpStatus.NOT_FOUND);
		}
		
	}

// getByCategory
	
	@GetMapping("/category/{category}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getByCategory(@PathVariable("category") String category){
		
		try {
			List<Product> product = productService.getallByCategory(category);
			return new ResponseEntity<>(product,HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Cant Find Products",HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	public ProductForm getJsonProduct(String etu) {
		
		
		ProductForm product =new ProductForm();
			try {
				ObjectMapper object=new ObjectMapper();
				product=object.readValue(etu,ProductForm.class);
				
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("error  : "+e.toString());
			}
			
			
			return product;
		}
	
//

}

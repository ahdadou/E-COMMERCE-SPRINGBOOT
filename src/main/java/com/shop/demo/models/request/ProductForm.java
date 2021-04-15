package com.shop.demo.models.request;



import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
	
	private String name;
	private float price;
	private String short_description;
	private String long_description;
	private int quantite;
	private String category;
//	@JsonPropertyDescription
//	private MultipartFile files;
	
	

}

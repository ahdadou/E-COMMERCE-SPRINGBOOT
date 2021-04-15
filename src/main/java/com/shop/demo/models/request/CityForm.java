package com.shop.demo.models.request;

import com.shop.demo.entity.Country;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CityForm {
	
	private String name;
	private String country;

}

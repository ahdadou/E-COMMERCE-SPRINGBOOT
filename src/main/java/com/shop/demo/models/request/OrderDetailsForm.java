package com.shop.demo.models.request;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.shop.demo.entity.Country;
import com.shop.demo.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsForm {
	
	private Long userId;
	private String name;
	private String phone;
	private String adress;
	private String city;
	private int zipCode;

}

package com.shop.demo.models.request;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
	
	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private Set<String> role;
	private String adress1;
	private String adress2;
	private String phone;
	private String country;
	private String city;
	private Date date_naissance;

}

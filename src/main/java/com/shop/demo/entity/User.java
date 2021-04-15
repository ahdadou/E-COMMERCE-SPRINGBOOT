package com.shop.demo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private Date date_register;
	private String adress1;
	private String adress2;
	private boolean enabled;
	private String phone;
	private Date date_naissance;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", 
 	joinColumns = @JoinColumn(name = "user_id"), 
 	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User(String username, String email, 
			String firstname, String lastname, 
			String password, String adress1,
			String adress2, String phone, 
			Date date_naissance) {
		super();
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.adress1 = adress1;
		this.adress2 = adress2;
		this.phone = phone;
		this.date_naissance = date_naissance;

	}
		
	

}

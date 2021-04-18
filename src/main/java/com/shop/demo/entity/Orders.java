package com.shop.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_order")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String name;
	private String phone;
	private String adress;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	private String city;
	private int zipCode;
	private Date date_order;
	

}

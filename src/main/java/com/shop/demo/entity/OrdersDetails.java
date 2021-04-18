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
@Table(name = "order_details")
public class OrdersDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_detail_id;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	
	private int quantity;
	
	private double price;

}

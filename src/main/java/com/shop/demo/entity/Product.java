package com.shop.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_id;
	private String name;
	private String img;
	private float price;
	private String short_description;
	private String long_description;
	private Date update_date;
	private int quantite;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id")
	private Category category;

}

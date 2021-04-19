package com.shop.demo.models.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartModule {
	
	private Long id;
	private int quantite;

}

package com.shop.demo.models.request;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderForm {
	
	private OrderDetailsForm orderDetails;
	private List<CartModule> orders;

}

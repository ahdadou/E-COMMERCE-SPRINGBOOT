package com.shop.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.demo.entity.Orders;
import com.shop.demo.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	public Orders saveOrder(Orders order) {
		return this.orderRepository.save(order);
	}

}

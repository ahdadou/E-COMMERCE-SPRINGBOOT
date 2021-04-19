package com.shop.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.demo.entity.OrdersDetails;
import com.shop.demo.repository.OrderRepository;
import com.shop.demo.repository.OrdersDetailsRepository;

@Service
public class OrdersDetailsService {
	
	
	@Autowired
	private OrdersDetailsRepository ordersDetailsRepository;
	
	
	public void saveOrders(OrdersDetails ord) {
		ordersDetailsRepository.save(ord);
	}

}

package com.shop.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.entity.Orders;
import com.shop.demo.entity.OrdersDetails;
import com.shop.demo.entity.Product;
import com.shop.demo.entity.User;
import com.shop.demo.models.request.OrderForm;
import com.shop.demo.security.UserDetailServcieImpl;
import com.shop.demo.services.OrderService;
import com.shop.demo.services.OrdersDetailsService;
import com.shop.demo.services.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class CheckoutController {
	
	@Autowired
	private OrdersDetailsService ordersDetailsService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserDetailServcieImpl userService;
	
	
	@PostMapping("/payement")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getPayement(){
		return new ResponseEntity<>("Payement Successfully",HttpStatus.OK);
	}
	
	@PostMapping("/new")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> addNewOrders(@RequestBody OrderForm orderForm){
		
		
//		Get User
		User user = userService.getById(orderForm.getOrderDetails().getUserId());
		
//		Add Order
		Date d = new Date();
		Orders ord = new Orders();
		ord.setUser(user);
		ord.setName(orderForm.getOrderDetails().getName());
		ord.setPhone(orderForm.getOrderDetails().getPhone());
		ord.setDate_order(d);
		ord = orderService.saveOrder(ord);
		
//		Get Product and save in detailsorders
		 for(int i=0;i<orderForm.getOrders().size();i++){
												Product p = productService.getProductById(orderForm.getOrders().get(i).getId());
												OrdersDetails od = new OrdersDetails();
												
												od.setOrder(ord);		
												od.setProduct(p);
												od.setQuantity(orderForm.getOrders().get(i).getQuantite());
												System.out.println("thiiis  :");
												System.out.println(orderForm.getOrders().get(i));
												ordersDetailsService.saveOrders(od);
										   };
		
		
		return new ResponseEntity<>(ord,HttpStatus.OK);
	}
	
	
	
	
	
	

}

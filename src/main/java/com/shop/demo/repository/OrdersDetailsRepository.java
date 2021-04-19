package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.OrdersDetails;

@Repository
public interface OrdersDetailsRepository extends JpaRepository<OrdersDetails, Long>{

}

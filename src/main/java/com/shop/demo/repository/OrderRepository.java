package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{

}

package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}

package com.shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.EnumRole;
import com.shop.demo.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(EnumRole adminRole);

}

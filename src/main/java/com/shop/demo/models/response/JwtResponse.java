package com.shop.demo.models.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public JwtResponse(String token, String username, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}
	
	

}

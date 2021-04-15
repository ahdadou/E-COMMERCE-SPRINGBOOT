package com.shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.models.request.LoginForm;
import com.shop.demo.models.response.JwtResponse;
import com.shop.demo.security.JWTProvider;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class LoginController {
	
	
	@Autowired
	private JWTProvider jwtProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {
 
		System.out.println(loginRequest.getUsername()+"    "+loginRequest.getPassword());
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = jwtProvider.generateJwtToken(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
		}catch(Exception ex) {
			return new ResponseEntity<>("USER OR PASSWORD SONT INCORRECT",HttpStatus.NOT_FOUND);

		}
	}

}

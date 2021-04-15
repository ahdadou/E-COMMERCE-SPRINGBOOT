package com.shop.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.demo.entity.City;
import com.shop.demo.entity.Country;
import com.shop.demo.entity.EnumRole;
import com.shop.demo.entity.Role;
import com.shop.demo.entity.User;
import com.shop.demo.models.request.RegisterForm;
import com.shop.demo.models.response.ResponseMessage;
import com.shop.demo.repository.RoleRepository;
import com.shop.demo.repository.UserRepository;
import com.shop.demo.services.CityService;
import com.shop.demo.services.CountryService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class RegisterController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CountryService countryservice;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterForm signUpRequest) {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account

		
		User user = new User(signUpRequest.getUsername(),
							signUpRequest.getEmail(),
							signUpRequest.getFirstname(),
							signUpRequest.getLastname(),
							encoder.encode(signUpRequest.getPassword()),
							signUpRequest.getAdress1(),
							signUpRequest.getAdress2(),
							signUpRequest.getPhone(),
							signUpRequest.getDate_naissance()
							);
		

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		try {
			
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN);
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(EnumRole.ROLE_USER);
					roles.add(userRole);
					break;
			}
				}
			);
			
		}catch(Exception ex) {
			Role userRole = roleRepository.findByName(EnumRole.ROLE_USER);
			roles.add(userRole);
		}
		user.setRoles(roles);
		
		Country c = countryservice.getByName(signUpRequest.getCountry());
		user.setCountry(c);
		
		City ct = cityService.getByName(signUpRequest.getCity());
		user.setCity(ct);
		
		Date currentUtilDate = new Date();
		user.setDate_register(currentUtilDate);
		user.setEnabled(true);
		
		
		userRepository.save(user);
				
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

}

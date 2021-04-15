package com.shop.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.demo.entity.User;
import com.shop.demo.repository.UserRepository;

@Service
public class UserDetailServcieImpl implements UserDetailsService{

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = new User();
		try {
			
			 user = userRepository.findByUsername(username);
		}catch (Exception e) {
			// TODO: handle exception
			new UsernameNotFoundException("User Not Found with -> username or email : " + username);
		}
		
		return MyUserPrincipal.build(user);
	}

}

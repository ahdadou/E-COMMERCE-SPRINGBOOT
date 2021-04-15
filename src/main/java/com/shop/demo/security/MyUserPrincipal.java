package com.shop.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.demo.entity.User;

public class MyUserPrincipal implements UserDetails{

	private Long id;

    private String firstname;
    
    private String lastname;

    private String username;

    private String email;

    @JsonIgnore
    private String password;
    
    private Collection<? extends GrantedAuthority> authorities;
    
    
    public MyUserPrincipal(Long id, String firstname, String lastname,
    		String username, String email, String password, 
    		Collection<? extends GrantedAuthority> authorities
    		) {
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.username = username;
			this.email = email;
			this.password = password;
			this.authorities = authorities;
			}
    
    public static MyUserPrincipal build(User user) {
    	
    	 List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> 
    	 			new SimpleGrantedAuthority(role.getName().name())
    			 ).collect(Collectors.toList());
    	 

        return new MyUserPrincipal(
                user.getUser_id(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

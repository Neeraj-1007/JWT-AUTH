package com.neoSoft.JWTBasedAuthentication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neoSoft.JWTBasedAuthentication.Repository.UserRepository;
import com.neoSoft.JWTBasedAuthentication.model.CustomUserService;
import com.neoSoft.JWTBasedAuthentication.model.User;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	User user=	userRepository.findByUsername(username);
		if(username.equals(user.getUsername())) {
			return new CustomUserService(user);
		}else {
			throw  new UsernameNotFoundException("NOT A USER");
		}
	}

}

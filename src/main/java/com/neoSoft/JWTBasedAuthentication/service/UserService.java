package com.neoSoft.JWTBasedAuthentication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoSoft.JWTBasedAuthentication.Repository.UserRepository;
import com.neoSoft.JWTBasedAuthentication.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional()
	public long save(User user) {
		User pk=userRepository.save(user);
		return pk.getId(); 
	}
}

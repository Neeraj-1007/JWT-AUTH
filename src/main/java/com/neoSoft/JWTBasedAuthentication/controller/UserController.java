package com.neoSoft.JWTBasedAuthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoSoft.JWTBasedAuthentication.model.User;
import com.neoSoft.JWTBasedAuthentication.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/save")
	public long save(@RequestBody User user) {
		long pk=service.save(user);
		return pk;
	}
}

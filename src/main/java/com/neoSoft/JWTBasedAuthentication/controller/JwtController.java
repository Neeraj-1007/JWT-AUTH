package com.neoSoft.JWTBasedAuthentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neoSoft.JWTBasedAuthentication.jwtHelper.JwtUtils;
import com.neoSoft.JWTBasedAuthentication.model.JwtRequest;
import com.neoSoft.JWTBasedAuthentication.model.JwtResponse;
import com.neoSoft.JWTBasedAuthentication.service.CustomUserDetailService;

@RestController
public class JwtController {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	 AuthenticationManager authManager;

	@RequestMapping(value="/token",method=RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		System.out.println("JWT REQUEST  "+jwtRequest);
		try {
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("BAD CREDENTIALS");
			
		}
		UserDetails userDetails=this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
	
	String token=this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT TOKEN===   "+token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}

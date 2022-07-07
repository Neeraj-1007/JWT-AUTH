package com.neoSoft.JWTBasedAuthentication.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.neoSoft.JWTBasedAuthentication.jwtHelper.JwtUtils;
import com.neoSoft.JWTBasedAuthentication.service.CustomUserDetailService;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils utils;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		get JWT
//		BEARER
//		VALIDATE
		System.out.println(request.getHeaderNames());
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		System.out.println(requestTokenHeader + " TOKEN");
//		check null and format of token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = utils.extractUsername(jwtToken);
				System.out.println(username + " USERNAME");
			} catch (Exception e) {
				e.printStackTrace();
			}

//	check security

			UserDetails userDetail = this.customUserDetailService.loadUserByUsername(username);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetail, null, userDetail.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				System.out.println(" not full filled contion 2");
			}

		}
		filterChain.doFilter(request, response);
	}

}

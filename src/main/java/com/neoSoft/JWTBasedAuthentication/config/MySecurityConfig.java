package com.neoSoft.JWTBasedAuthentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.neoSoft.JWTBasedAuthentication.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JWTAuthenticationFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
			.cors()
			.disable()
			.authorizeRequests()
			.antMatchers("/token").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	/*
	 * @Bean
	 * public BCryptPasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

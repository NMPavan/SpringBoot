package com.example.HealthCareMini.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService ud;
	
	@Autowired
	private BCryptPasswordEncoder bd;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(ud).passwordEncoder(bd);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
	}
	
	

}

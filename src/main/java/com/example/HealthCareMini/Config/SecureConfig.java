package com.example.HealthCareMini.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.HealthCareMini.constants.UserRoles;

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
		http.authorizeRequests()
     	.antMatchers("/Patient/register","/Patient/save","/user/showForgot","/user/genNewPwd").permitAll()
     	.antMatchers("/spec/**").hasAuthority(UserRoles.ADMIN.name())
        .antMatchers("/doctor/**").hasAuthority(UserRoles.ADMIN.name())
		
		
		
		//.antMatchers("/doc/**").hasAuthority(UserRoles.ADMIN.name())
		
		.antMatchers("/Appointment/register","/Appointment/save","/Appointment/all").hasAuthority(UserRoles.ADMIN.name())
		
		.antMatchers("/appointment/view","/appointment/viewslots").hasAuthority(UserRoles.PATIENT.name())
		//.antMatchers("/slots/book","/slots/cancel").hasAuthority(UserRoles.PATIENT.name())
		//.antMatchers("/slots/all","/slots/accept","/slots/reject","/slots/dashboard").hasAuthority(UserRoles.ADMIN.name())
		
		//.antMatchers("/user/login","/login").permitAll()
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		//.loginPage("/user/login") //show Login Page
		//.loginProcessingUrl("/login") //POST (do login)
		.defaultSuccessUrl("/spec/all",true)
		//.failureUrl("/user/login?error=true") //If login is failed
		
		.and()
		.logout();
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //URL for Logout
//		.logoutSuccessUrl("/user/login?logout=true") // On logout success
		

	}

}

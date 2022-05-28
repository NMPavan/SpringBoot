package com.example.HealthCareMini.services.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.User;
import com.example.HealthCareMini.repo.UserRepoistory;
import com.example.HealthCareMini.services.IUserService;

@Service
public class IUserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepoistory repo;

	@Override
	public Long saveUser(User user) {

		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = findByUsername(username);
		if (!opt.isPresent())
			throw new UsernameNotFoundException(username);
		else {
			User user = opt.get();
			System.out.println("user"+ user);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

		}
	}

}

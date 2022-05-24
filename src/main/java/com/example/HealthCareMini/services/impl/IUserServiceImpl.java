package com.example.HealthCareMini.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.User;
import com.example.HealthCareMini.repo.UserRepoistory;
import com.example.HealthCareMini.services.IUserService;

@Service
public class IUserServiceImpl implements IUserService {

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

}

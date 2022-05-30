package com.example.HealthCareMini.services;

import java.util.Optional;

import com.example.HealthCareMini.Entity.User;

public interface IUserService {
	Long saveUser(User user);
	Optional<User> findByUsername(String username);
	
	void updatePassword(String encPwd,Long userId);
}

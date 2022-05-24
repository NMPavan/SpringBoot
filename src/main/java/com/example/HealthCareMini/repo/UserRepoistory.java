package com.example.HealthCareMini.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.User;

@Repository
public interface UserRepoistory extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}

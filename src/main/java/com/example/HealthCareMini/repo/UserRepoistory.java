package com.example.HealthCareMini.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.User;

@Repository
public interface UserRepoistory extends JpaRepository<User, Long> {
	
	
	@Modifying
	@Query("UPDATE User SET password =:encPwd WHERE id = :userId")
	void updatePassword(String encPwd,Long userId);

	Optional<User> findByUsername(String username);
}

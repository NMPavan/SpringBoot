package com.example.HealthCareMini.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.Patient;


@Repository
public interface PatientRepoistory extends JpaRepository<Patient, Long> {
	Optional<Patient> findByEmail(String email);
}

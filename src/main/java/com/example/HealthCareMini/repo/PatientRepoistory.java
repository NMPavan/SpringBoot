package com.example.HealthCareMini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.Patient;


@Repository
public interface PatientRepoistory extends JpaRepository<Patient, Long> {

}

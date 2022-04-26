package com.example.HealthCareMini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HealthCareMini.Entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

}

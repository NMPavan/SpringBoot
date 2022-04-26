package com.example.HealthCareMini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

}

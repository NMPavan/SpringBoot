package com.example.HealthCareMini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}

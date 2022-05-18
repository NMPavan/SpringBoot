package com.example.HealthCareMini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.Appointment;

@Repository
public interface AppointmentRepoistory extends JpaRepository<Appointment, Long> {

}

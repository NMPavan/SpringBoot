package com.example.HealthCareMini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	@Query("SELECT id, firstName, lastName FROM Doctor")
	public List<Object[]> getDoctorIdAndNames();
	
	@Query("SELECT doct FROM Doctor doct INNER JOIN doct.specId as spc WHERE spc.Id=:specId")
	public List<Doctor> findDoctorBySpecId(Long specId);
}

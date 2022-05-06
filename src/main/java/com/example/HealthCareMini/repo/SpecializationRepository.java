package com.example.HealthCareMini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	
	@Query("select count(code) from Specialization where code=:code")
	Integer getSpecializationCodeCount(String code);
	
	@Query("select count(name) from Specialization where name=:name")
	Integer getSpecializationNameCount(String name);
	
	@Query("select count(note) from Specialization where note=:note")
	Integer getSpecializationNoteCount(String note);
	
	@Query("SELECT COUNT(code) FROM Specialization  WHERE code=:code AND Id!=:Id")
	Integer getSpecCodeCountForEdit(String code,Long Id);
	
	@Query("SELECT COUNT(name) FROM Specialization  WHERE name=:name AND Id!=:Id")
	Integer getSpecNameCountForEdit(String name,Long Id);
	}

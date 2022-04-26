package com.example.HealthCareMini.services;

import java.util.List;

import com.example.HealthCareMini.Entity.Specialization;

public interface ISpecializationService {

	public void saveSpecializedData(Specialization se);
	public List<Specialization> getAllSpecializedData();
	
	public void removeSpecialization(Long id);
	public Specialization getSpecialization(Long id);
}
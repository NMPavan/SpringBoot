package com.example.HealthCareMini.services;

import java.util.List;
import java.util.Map;

import com.example.HealthCareMini.Entity.Specialization;

public interface ISpecializationService {

	public Long saveSpecializedData(Specialization se);
	public List<Specialization> getAllSpecializedData();
	
	public void removeSpecialization(Long id);
	public Specialization getSpecialization(Long id);
	void updateSpecializationData(Specialization se);
	
	//ajax validations for specialization module
	boolean isSpecCodeExist(String code);
	
	boolean isSpecNameExist(String name);
	
	boolean isSpecNoteExist(String note);
	
	
	public boolean isSpecCodeExistForEdit(String code,Long Id);
	
	public boolean isSpecNameExistForEdit(String name,Long Id);
	
	Map<Long,String> getIdAndNameFromSpecia();
}

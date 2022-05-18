package com.example.HealthCareMini.services;

import java.util.List;
import java.util.Map;

import com.example.HealthCareMini.Entity.Doctor;

public interface DoctorService {

	
	public Long saveDoctorData(Doctor d);
	public List<Doctor> getAllDoctorData();
	
	public void removeDoctorRecord(Long id);
	public Doctor getDoctor(Long id);
	void updateDoctorData(Doctor d);
	
	public Map<Long,String> getDoctorIdAndNames();
}

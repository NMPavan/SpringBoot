package com.example.HealthCareMini.services;

import java.util.List;

import com.example.HealthCareMini.Entity.Patient;


public interface PatientService {

	Long savePatient(Patient patient);

	void updatePatient(Patient patient);

	void deletePatient(Long id);

	public Patient getOnePatient(Long id);

	List<Patient> getAllPatients();

	Patient getOneByEmail(String email);

	long getPatientCount();
}

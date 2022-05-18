package com.example.HealthCareMini.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.Patient;
import com.example.HealthCareMini.Exception.PatientException;
import com.example.HealthCareMini.repo.PatientRepoistory;
import com.example.HealthCareMini.services.PatientService;


@Service
public class IPatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepoistory repo;

	@Override
	public Long savePatient(Patient patient) {
		return repo.save(patient).getId();
	}

	@Override
	public void updatePatient(Patient patient) {
		repo.save(patient);

	}

	@Override
	public void deletePatient(Long id) {
		repo.delete(getOnePatient(id));
	}

	@Override
	public Patient getOnePatient(Long id) {
		return repo.findById(id).orElseThrow(() -> new PatientException("Id not Found"));
	}

	@Override
	public List<Patient> getAllPatients() {
		return repo.findAll();
	}

	@Override
	public Patient getOneByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPatientCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}

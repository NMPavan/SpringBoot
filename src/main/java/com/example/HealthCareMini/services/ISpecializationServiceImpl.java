package com.example.HealthCareMini.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.Specialization;
import com.example.HealthCareMini.Exception.SpecializationException;
import com.example.HealthCareMini.repo.SpecializationRepository;

@Service
public class ISpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecializedData(Specialization se) {
		return repo.save(se).getId();

	}

	@Override
	public List<Specialization> getAllSpecializedData() {
		return repo.findAll();
	}

	// implemented the custom Exception handling for delete and edit api

	@Override
	public void removeSpecialization(Long id) {
		repo.delete(getSpecialization(id));
	}

	@Override
	public Specialization getSpecialization(Long id) {
		Optional<Specialization> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new SpecializationException(id + " Not Found");
		}
		/*
		 * return repo.findById(id).orElseThrow( ()-> new
		 * SpecializationNotFoundException(id+ " Not Found") );
		 */

	}

	@Override
	public void updateSpecializationData(Specialization se) {
		repo.save(se);

	}

	@Override
	public boolean isSpecCodeExist(String code) {
		// TODO Auto-generated method stub
		return repo.getSpecializationCodeCount(code) > 0;
	}

	@Override
	public boolean isSpecNameExist(String name) {
		// TODO Auto-generated method stub
		return repo.getSpecializationNameCount(name) > 0;
	}

	@Override
	public boolean isSpecNoteExist(String note) {
		// TODO Auto-generated method stub
		return repo.getSpecializationNoteCount(note) > 0;
	}

	@Override
	public boolean isSpecCodeExistForEdit(String code, Long id) {
		// TODO Auto-generated method stub
		
	//	System.out.println("count"+ repo.getSpecCodeCountForEdit(code,id));
		 return repo.getSpecCodeCountForEdit(code,id) > 0;
	}

	@Override
	public boolean isSpecNameExistForEdit(String name, Long Id) {
		return repo.getSpecNameCountForEdit(name, Id) > 0;
	}
	


}

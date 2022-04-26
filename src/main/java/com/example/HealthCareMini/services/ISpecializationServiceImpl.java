package com.example.HealthCareMini.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.HealthCareMini.Entity.Specialization;
import com.example.HealthCareMini.repo.SpecializationRepository;

public class ISpecializationServiceImpl implements ISpecializationService {
	
	@Autowired
	private SpecializationRepository repo;

	@Override
	public void saveSpecializedData(Specialization se) {
		repo.save(se);

	}

	@Override
	public List<Specialization> getAllSpecializedData() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public Specialization getSpecialization(Long id) {
		Optional<Specialization> sep = repo.findById(id);
		if(sep.isPresent()) {
			Specialization se = sep.get();
			return se;
		}
		return null;
	}

}
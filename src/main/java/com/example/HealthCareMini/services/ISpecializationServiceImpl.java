package com.example.HealthCareMini.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.Specialization;
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

	@Override
	public void removeSpecialization(Long id) {
		repo.deleteById(id);

	}

	@Override
	public Specialization getSpecialization(Long id) {
		Optional<Specialization> sep = repo.findById(id);
		if (sep.isPresent()) {
			Specialization se = sep.get();
			return se;
		}
		return null;
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
		return repo.getSpecializationNameCount(name) > 0 ;
	}

}

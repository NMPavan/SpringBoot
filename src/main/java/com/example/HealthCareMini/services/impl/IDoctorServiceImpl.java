package com.example.HealthCareMini.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.Doctor;
import com.example.HealthCareMini.Exception.DoctorException;
import com.example.HealthCareMini.repo.DoctorRepository;
import com.example.HealthCareMini.services.DoctorService;

@Service
public class IDoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository docrepo;

	@Override
	public Long saveDoctorData(Doctor d) {
		return docrepo.save(d).getId();
	}

	@Override
	public List<Doctor> getAllDoctorData() {
		return docrepo.findAll();
	}

	@Override
	public void removeDoctorRecord(Long id) {
		docrepo.delete(getDoctor(id));
	}

	@Override
	public Doctor getDoctor(Long id) {

//		Optional<Doctor> opt = docrepo.findById(id);
//		
//		if(opt.isPresent()) {
//			Doctor d = opt.get();
//			return d;
//		}else {
//			throw new DoctorException("Id not Found");
//		}

		return docrepo.findById(id).orElseThrow(() -> new DoctorException("Id not Found"));
	}

	@Override
	public void updateDoctorData(Doctor d) {
		docrepo.save(d);
	}

	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		List<Object[]> list = docrepo.getDoctorIdAndNames();
		Map<Long, String> map = list.stream().collect(Collectors.toMap(ob -> Long.valueOf(ob[0].toString()),
				ob -> ob[1].toString() + " " + ob[2].toString()));
		return map;
	}

}

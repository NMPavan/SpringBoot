package com.example.HealthCareMini.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.Patient;
import com.example.HealthCareMini.Entity.User;
import com.example.HealthCareMini.Exception.PatientException;
import com.example.HealthCareMini.Utility.MyMailUtil;
import com.example.HealthCareMini.Utility.UserUtil;
import com.example.HealthCareMini.constants.UserRoles;
import com.example.HealthCareMini.repo.PatientRepoistory;
import com.example.HealthCareMini.services.IUserService;
import com.example.HealthCareMini.services.PatientService;

@Service
public class IPatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepoistory repo;

	@Autowired
	private IUserService userService;

	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailUtil mailUtil;
	@Autowired
	private BCryptPasswordEncoder bd;

	@Override
	@Transactional
	public Long savePatient(Patient patient) {
		Long id = repo.save(patient).getId();
		if (id != null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(patient.getFirstName() + " " + patient.getLastName());
			user.setUsername(patient.getEmail());
			user.setPassword(bd.encode(pwd));
			user.setRole(UserRoles.PATIENT.toString());
			Long genId = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your uname is " + patient.getEmail() +", password is "+ pwd;
						mailUtil.send(patient.getEmail(), "PATIENT ADDED", text);
					}
				}).start();
		}
		return id;
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

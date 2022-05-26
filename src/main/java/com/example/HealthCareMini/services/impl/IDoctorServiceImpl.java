package com.example.HealthCareMini.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.Doctor;
import com.example.HealthCareMini.Entity.User;
import com.example.HealthCareMini.Exception.DoctorException;
import com.example.HealthCareMini.Utility.MyMailUtil;
import com.example.HealthCareMini.Utility.UserUtil;
import com.example.HealthCareMini.constants.UserRoles;
import com.example.HealthCareMini.repo.DoctorRepository;
import com.example.HealthCareMini.services.DoctorService;
import com.example.HealthCareMini.services.IUserService;

@Service
public class IDoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository docrepo;

	@Autowired
	private IUserService userService;

	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailUtil mailUtil ;

	@Override
	@Transactional
	public Long saveDoctorData(Doctor d) {
		Long id = docrepo.save(d).getId();
		if (id != null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(d.getFirstName() + " " + d.getLastName());
			user.setUsername(d.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name());
			Long genId = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your uname is " + d.getEmail() +", password is "+ pwd;
						mailUtil.send(d.getEmail(), "DOCTOR ADDED", text);
					}
				}).start();
		}
		return id;
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

	@Override
	public List<Doctor> findDoctorBySpecId(Long specId) {
		return docrepo.findDoctorBySpecId(specId);
	}

}

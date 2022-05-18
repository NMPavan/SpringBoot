package com.example.HealthCareMini.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.Appointment;
import com.example.HealthCareMini.Exception.AppointmentException;
import com.example.HealthCareMini.repo.AppointmentRepoistory;
import com.example.HealthCareMini.services.AppointmentService;

@Service
public class IAppointmentService implements AppointmentService {

	@Autowired
	private AppointmentRepoistory apo;

	@Override
	public Long saveAppointmentData(Appointment d) {
		return apo.save(d).getId();
	}

	@Override
	public List<Appointment> getAllAppointmentData() {
		List<Appointment> list = apo.findAll();
		return list;
	}

	@Override
	public void removeAppointmentRecord(Long id) {
		apo.delete(getAppointmentRecord(id));
	}

	@Override
	public Appointment getAppointmentRecord(Long id) {
		return apo.findById(id).orElseThrow(() -> new AppointmentException("Id not Found"));
	}

	@Override
	public void updateAppointmentData(Appointment d) {
		apo.save(d);
	}

}

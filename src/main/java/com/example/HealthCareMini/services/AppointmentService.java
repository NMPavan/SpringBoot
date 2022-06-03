package com.example.HealthCareMini.services;

import java.util.List;

import com.example.HealthCareMini.Entity.Appointment;

public interface AppointmentService {
	
	public Long saveAppointmentData(Appointment d);
	public List<Appointment> getAllAppointmentData();
	
	public void removeAppointmentRecord(Long id);
	public Appointment getAppointmentRecord(Long id);
	void updateAppointmentData(Appointment d);
	List<Object[]> getAppoinmentsByDoctor(Long docId);
	
	List<Object[]> getAppointsmentsByDocUserName(String userName);
}

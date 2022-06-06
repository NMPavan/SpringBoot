package com.example.HealthCareMini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.HealthCareMini.Entity.Appointment;

@Repository
public interface AppointmentRepoistory extends JpaRepository<Appointment, Long> {
	
	@Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee, aptm.id from Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId and aptm.noOfSlots > 0")
	public List<Object[]> getAppointmentByDocId(Long docId);
	
	
	@Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee, aptm.details from Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.email=:userName and aptm.noOfSlots > 0")
	public List<Object[]> getAppointmentsByDocEmail(String userName);
	
	
	@Modifying
	@Query("UPDATE Appointment SET noOfSlots = noOfSlots + :count WHERE id=:id")
	void updateSlotCountForAppoinment(Long id,int count);

}

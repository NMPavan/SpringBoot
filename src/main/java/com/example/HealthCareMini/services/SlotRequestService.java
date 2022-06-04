package com.example.HealthCareMini.services;

import java.util.List;

import com.example.HealthCareMini.Entity.SlotRequest;

public interface SlotRequestService {

	// patient can book slot
	Long saveSlotRequest(SlotRequest sr);

	// fetch one
	SlotRequest getOneSlotRequest(Long id);

	// ADMIN can view all slots
	List<SlotRequest> getAllSlotRequests();

	// ADMIN/patient can update status
	void updateSlotRequestStatus(Long id, String status);

	// PATIENT can see his slots
	List<SlotRequest> viewSlotsByPatientMail(String patientMail);
}

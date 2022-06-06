package com.example.HealthCareMini.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HealthCareMini.Entity.SlotRequest;
import com.example.HealthCareMini.constants.SlotReqStatus;
import com.example.HealthCareMini.repo.SlotRequestRepo;
import com.example.HealthCareMini.services.SlotRequestService;

@Service
public class ISlotRequestServiceImpl implements SlotRequestService {

	@Autowired
	private SlotRequestRepo slot;

	@Override
	public Long saveSlotRequest(SlotRequest sr) {
		return slot.save(sr).getId();
	}

	@Override
	public SlotRequest getOneSlotRequest(Long id) {
		Optional<SlotRequest> opt = slot.findById(id);
		if (opt != null) {
			return opt.get();
		}
		return null;
	}

	@Override
	public List<SlotRequest> getAllSlotRequests() {
		return slot.findAll();
	}

	@Transactional
	public void updateSlotRequestStatus(Long id, String status) {
		slot.updateSlotRequestStatus(id, status);

	}

	@Override
	public List<SlotRequest> viewSlotsByPatientMail(String patientMail) {
		return slot.getAllPatientSlots(patientMail);
	}

	@Override
	public List<SlotRequest> viewSlotsByDoctorMail(String doctorMail) {
		return slot.getAllDoctorSlots(doctorMail, SlotReqStatus.ACCEPTED.name());
	}
}

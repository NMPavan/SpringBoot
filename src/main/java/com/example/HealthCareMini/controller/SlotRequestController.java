package com.example.HealthCareMini.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HealthCareMini.Entity.Appointment;
import com.example.HealthCareMini.Entity.Patient;
import com.example.HealthCareMini.Entity.SlotRequest;
import com.example.HealthCareMini.Entity.User;
import com.example.HealthCareMini.constants.SlotReqStatus;
import com.example.HealthCareMini.services.PatientService;
import com.example.HealthCareMini.services.impl.IAppointmentService;
import com.example.HealthCareMini.services.impl.ISlotRequestServiceImpl;

@Controller
@RequestMapping("/slots")
public class SlotRequestController {
	
	
	@Autowired
	private IAppointmentService apo;
	
	@Autowired
	private PatientService patient1;
	

	@Autowired
	private ISlotRequestServiceImpl service;
	
	
	@GetMapping("/book")
	private String bookAppointment(@RequestParam Long appid,Model model,HttpSession session) {
		
		Appointment app = apo.getAppointmentRecord(appid);

		//for patient object
		User user = (User) session.getAttribute("userOb");
		String email = user.getUsername();
		Patient patient = patient1.getOneByEmail(email);

		// create slot object
		SlotRequest sr = new SlotRequest();
		sr.setAppointment(app);
		sr.setPatient(patient);
		sr.setStatus("PENDING");
		
		System.out.println("sr" + sr);
		try {

			service.saveSlotRequest(sr);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			String appDte = sdf.format( app.getDate());

			String message = " Patient " + (patient.getFirstName()+" "+patient.getLastName())
					+", Request for Dr. " + app.getDoctor().getFirstName() +" "+app.getDoctor().getLastName()
					+", On Date : " + appDte +", submitted with status: "+sr.getStatus();

			model.addAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "BOOKING REQUEST ALREADY MADE FOR THIS APPOINTMENT/DATE");
		}

		return "SlotRequestMesage";
	}
	
	
	@GetMapping("/all")
	public String viewAllReq(Model model) {
		List<SlotRequest> list = service.getAllSlotRequests();
		model.addAttribute("list", list);
		return "SlotRequestData";
	}
	
	
	@GetMapping("/patient")
	public String getAllPatientSlots(Model model,HttpSession session) {
		
		User user = (User) session.getAttribute("userOb");
		String email = user.getUsername();
		
		List<SlotRequest> list1 = service.viewSlotsByPatientMail(email);
		model.addAttribute("list", list1);
		return "SlotRequestDataPatient";
	}
	
	@GetMapping("/doctor")
	public String viewMyReqDoc(
			Principal principal,
			Model model) 
	{
		String email = principal.getName();
		List<SlotRequest> list = service.viewSlotsByDoctorMail(email);
		model.addAttribute("list", list);
		return "SlotRequestDataDoctor";
	}
	
	@GetMapping("/accept")
	public String updateSlotAccept(
			@RequestParam Long id
			) 
	{
		service.updateSlotRequestStatus(id, SlotReqStatus.ACCEPTED.name());
		SlotRequest sr = service.getOneSlotRequest(id);
		if(sr.getStatus().equals(SlotReqStatus.ACCEPTED.name())) {
			System.out.println("id" + id);
			apo.updateSlotCountForAppoinment(
					sr.getAppointment().getId(), -1);
		}
		return "redirect:all";
	}
	
	@GetMapping("/reject")
	public String updateSlotReject(
			@RequestParam Long id
			) 
	{
		service.updateSlotRequestStatus(id, SlotReqStatus.REJECTED.name());
		return "redirect:all";
	}
	
	
	@GetMapping("/cancel")
	public String cancelSlotReject(
			@RequestParam Long id
			) 
	{
		SlotRequest sr = service.getOneSlotRequest(id);
		if(sr.getStatus().equals(SlotReqStatus.ACCEPTED.name())) {
			service.updateSlotRequestStatus(id, SlotReqStatus.CANCELLED.name());
			apo.updateSlotCountForAppoinment(
					sr.getAppointment().getId(), 1);
		}
		return "redirect:patient";
	}
	

}

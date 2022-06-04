package com.example.HealthCareMini.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.HealthCareMini.Entity.Appointment;
import com.example.HealthCareMini.Entity.Doctor;
import com.example.HealthCareMini.Exception.DoctorException;
import com.example.HealthCareMini.services.DoctorService;
import com.example.HealthCareMini.services.ISpecializationService;
import com.example.HealthCareMini.services.impl.IAppointmentService;

@Controller
@RequestMapping("/Appointment")
public class AppointmentController {

	@Autowired
	private IAppointmentService apo;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ISpecializationService specializationService;

	private void commonUi(Model model) {
		model.addAttribute("doctors", doctorService.getDoctorIdAndNames());
	}

	@GetMapping("/register")
	public String getAppointmentRegisterPage(@RequestParam(value = "message", required = false) String message,
			Model model) {
		model.addAttribute("message", message);
		commonUi(model);
		return "AppointmentRegister";
	}

	@PostMapping("/save")
	public String saveAppointmentData(@ModelAttribute Appointment d, Model model) {
		Long id = apo.saveAppointmentData(d);
		// model.addAttribute("message", "Appointment created with Id:" + id);
		// model.addAttribute("appointment", new Appointment());
		commonUi(model);
		return "AppointmentRegister";
	}

	@GetMapping("/all")
	public String getAllAppointments(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Appointment> list = apo.getAllAppointmentData();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "AppointmentData";
	}
//	@GetMapping("/edit")
//	public String updateDoctorDataPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
//		String page = null;
//		try {
//			Doctor d = ds.getDoctor(id);
//			model.addAttribute("doctor", d);
//			//createDyanamicUi(model);
//			page = "DoctorEdit";
//		} catch (DoctorException e) {
//			e.printStackTrace();
//			attributes.addAttribute("message", e.getMessage());
//			page = "redirect:all";
//		}
//		return page;
//	}

//	@PostMapping("/update")
//	public String update(@ModelAttribute Doctor d, RedirectAttributes attributes) {
//		ds.updateDoctorData(d);
//		attributes.addAttribute("message", "Record " + " " + d.getId() + "=" + "update successfully ");
//		return "redirect:all";
//	}

	@GetMapping("/view")
	public String getDoctorBySpecId(Model model, @RequestParam(defaultValue = "0", required = false) Long specId) {

		// HERE WE ARE JUST INTEGRATING THE SPECIALIZATION MODULE
		Map<Long, String> specMap = specializationService.getIdAndNameFromSpecia();
		model.addAttribute("specializations", specMap);
		List<Doctor> docList = null;
		String message = null;
		if (specId <= 0) {
			docList = doctorService.getAllDoctorData();
			message = "Result : All Doctors";
		} else {
			docList = doctorService.findDoctorBySpecId(specId);
			message = "Result : " + specializationService.getSpecialization(specId).getName() + " Doctors";

		}
		model.addAttribute("docList", docList);
		model.addAttribute("message", message);
		return "AppointmentSearch";
	}
	
	
	//HERE WE ARE JUST INTEGRATING DOCTOR DB INTO APPOINTMENT TABLE
	//FOR THAT WE ARE WRITING HQL QUERY BY JOINING APPOINTMNET AND DOCTOR TABLE SO 
	//FETCH APPOINTMENT DATA BASED ON DOCTOR ID
	
	@GetMapping("/viewslots")
	public String getAppointmentsByDocId(@RequestParam Long id,Model model) {
		List<Object[]> list =  apo.getAppoinmentsByDoctor(id);
		model.addAttribute("list", list);
		return "AppointmentSlots";
	}
	
	
	@GetMapping("/currentDoc")
	public String getCurrentDocAppointments(
			Model model,
			Principal p) 
	{
		List<Object[]> list=apo.getAppointsmentsByDocUserName(p.getName());
		model.addAttribute("list",list);
		return "AppointmentForDoctor";
	}
	
	

}

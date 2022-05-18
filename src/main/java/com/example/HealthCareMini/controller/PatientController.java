package com.example.HealthCareMini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.HealthCareMini.Entity.Patient;
import com.example.HealthCareMini.Exception.PatientException;
import com.example.HealthCareMini.services.PatientService;

@Controller
@RequestMapping("/Patient")
public class PatientController {

	@Autowired
	private PatientService patient;

	@GetMapping("/register")
	public String getDoctRegisterPage(@RequestParam(value = "message", required = false) String message, Model model) {
		model.addAttribute("message", message);
		// createDyanamicUi(model);
		return "PatientRegister";
	}

	@PostMapping("/save")
	public String saveDoctorData(@ModelAttribute Patient patientData, RedirectAttributes attributes) {
		Long id = patient.savePatient(patientData);
		String message = id + "Patient record registered";
		attributes.addAttribute("message", message);
		return "redirect:all";
	}
	
	@GetMapping("/all")
	public String getAllPatients(Model model,
			@RequestParam(value = "message", required = false) String message) {
		List<Patient> list=patient.getAllPatients();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "PatientData";
	}
	
	
	@GetMapping("/edit")
	public String updatePatientPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Patient d = patient.getOnePatient(id);
			model.addAttribute("patient", d);
			//createDyanamicUi(model);
			page = "PatientEdit";
		} catch (PatientException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Patient d, RedirectAttributes attributes) {
		patient.updatePatient(d);
		attributes.addAttribute("message", "Record " + " " + d.getId() + "=" + "update successfully ");
		return "redirect:all";
	}

	
	
	
}

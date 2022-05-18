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

import com.example.HealthCareMini.Entity.Doctor;
import com.example.HealthCareMini.Exception.DoctorException;
import com.example.HealthCareMini.services.DoctorService;
import com.example.HealthCareMini.services.ISpecializationService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService ds;

	@Autowired
	private ISpecializationService s;

	private void createDyanamicUi(Model model) {
		model.addAttribute("specializations", s.getIdAndNameFromSpecia());
	}

	@GetMapping("/register")
	public String getDoctRegisterPage(@RequestParam(value = "message", required = false) String message, Model model) {
		model.addAttribute("message", message);
		createDyanamicUi(model);
		return "DoctorRegister";
	}

	@PostMapping("/save")
	public String saveDoctorData(@ModelAttribute Doctor d, RedirectAttributes attributes) {
		Long id = ds.saveDoctorData(d);
		String message = id + "Doctor record registered";
		attributes.addAttribute("message", message);
		return "redirect:all";
	}

	@GetMapping("/all")
	public String getAllDoctorData(@RequestParam(value = "message", required = false) String message, Model model) {
		List<Doctor> data = ds.getAllDoctorData();
		model.addAttribute("list", data);
		model.addAttribute("message", message);
		return "DoctorData";
	}

	@GetMapping("/edit")
	public String updateDoctorDataPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Doctor d = ds.getDoctor(id);
			model.addAttribute("doctor", d);
			createDyanamicUi(model);
			page = "DoctorEdit";
		} catch (DoctorException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Doctor d, RedirectAttributes attributes) {
		ds.updateDoctorData(d);
		attributes.addAttribute("message", "Record " + " " + d.getId() + "=" + "update successfully ");
		return "redirect:all";
	}

	@GetMapping("/delete")
	public String deleteDoctorData(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			ds.removeDoctorRecord(id);
			attributes.addAttribute("message", "Record (" + id + ") is removed");
		} catch (DoctorException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}

}

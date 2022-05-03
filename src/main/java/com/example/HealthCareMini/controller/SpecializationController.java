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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.HealthCareMini.Entity.Specialization;
import com.example.HealthCareMini.Exception.SpecializationException;
import com.example.HealthCareMini.View.SpecializationExcelView;
import com.example.HealthCareMini.services.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService spes;

	@GetMapping("/all")
	public String getAllSpecializationData(Model model,
			@RequestParam(value = "message", required = false) String message) {
		System.out.println("inside all" + message);
		List<Specialization> spe = spes.getAllSpecializedData();
		model.addAttribute("list", spe);
		model.addAttribute("message", message);
		return "SpecializationData";
	}

	@GetMapping("/register")
	public String getAllSpecializationDataPage() {
		return "SpecializationRegister";
	}

	@PostMapping("/save")
	public String saveSpecializationRecord(@ModelAttribute Specialization se, RedirectAttributes attributes) {
		Long id = spes.saveSpecializedData(se);
		String message = "Specialization Record '" + id + "'created";
		attributes.addAttribute("message", message);
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String updateSpecializationDataPage(@RequestParam Long Id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Specialization spec = spes.getSpecialization(Id);
			model.addAttribute("specialization", spec);
			page = "SpecializationEdit";
		} catch (SpecializationException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Specialization se, RedirectAttributes attributes) {
		spes.updateSpecializationData(se);
		attributes.addAttribute("message", "Record " + " " + se.getId() + "=" + "update successfully ");
		return "redirect:all";
	}

	@GetMapping("/delete")
	public String deleteSpecializationData(@RequestParam Long Id, RedirectAttributes attributes) {
		try {
			spes.removeSpecialization(Id);
			attributes.addAttribute("message", "Record (" + Id + ") is removed");
		} catch (SpecializationException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}

	// ajax validations specialization module

	@GetMapping("/checkcode")
	@ResponseBody
	public String specializationCodeCheck(@RequestParam String code) {
		String message = "";
		if (spes.isSpecCodeExist(code)) {
			message = code + " Already exist";
		}
		return message;
	}

	@GetMapping("/checkname")
	@ResponseBody
	public String specializationNameCheck(@RequestParam String name) {
		String message = "";
		if (spes.isSpecNameExist(name)) {
			message = name + " Already exist";
		}
		return message;
	}

	@GetMapping("/checknote")
	@ResponseBody
	public String specializationNoteCheck(@RequestParam String note) {
		String message = "";
		if (spes.isSpecNoteExist(note)) {
			message = note + "Already exist";
		}
		return message;
	}

	@GetMapping("/excel")
	public ModelAndView specializationExcelShow() {

		// Here need to return ModelandView
		// define modelandview class
		ModelAndView m = new ModelAndView();

		// set the excelfuntion implemented class
		m.setView(new SpecializationExcelView());

		// get the data from db
		List<Specialization> list = spes.getAllSpecializedData();
		// send the data to excelview implemented class
		m.addObject("list", list);
		// return the modelandview
		return m;
	}

}

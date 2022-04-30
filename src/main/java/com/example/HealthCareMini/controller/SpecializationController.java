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

import com.example.HealthCareMini.Entity.Specialization;
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
	public String saveSpecializationRecord(@ModelAttribute Specialization se, 
			RedirectAttributes attributes) {
		Long id = spes.saveSpecializedData(se);
		//model.addAttribute("message", id);
		String message = "Specialization Record '" + id + "'created";
		attributes.addAttribute("message", message);
		//model.addAttribute("message", message);
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String updateSpecializationDataPage(@RequestParam Long Id, Model model) {
		Specialization se = spes.getSpecialization(Id);
		model.addAttribute("specialization", se);
		return "SpecializationEdit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Specialization se, RedirectAttributes attributes) {
		spes.updateSpecializationData(se);
		attributes.addAttribute("message", "Record " + " " + se.getId() + "=" + "update successfully ");
		return "redirect:all";
	}

	@GetMapping("/delete")
	public String deleteSpecializationData(@RequestParam Long Id, RedirectAttributes attributes) {
		spes.removeSpecialization(Id);
		attributes.addAttribute("message", "Record (" + Id + ")is removed");
		return "redirect:all";
	}

}

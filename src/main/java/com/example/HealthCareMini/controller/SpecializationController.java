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
	public  String getAllSpecializationData(Model model,@RequestParam(value = "message",required = false) String message) {
		List<Specialization> spe = spes.getAllSpecializedData();
		model.addAttribute("list", spe);
		model.addAttribute("message",message);
		return "SpecializationData";
	}
	
	
	@GetMapping("/register")
	public  String getAllSpecializationDataPage() {
	
		return "SpecializationRegister";
	}
	
	
	@PostMapping("/save")
	public String saveSpecializationRecord(Model model, @ModelAttribute Specialization se) {
		Long id = spes.saveSpecializedData(se);
		model.addAttribute("message", id);
		
		String message = "Specialization Record '" + id + "'created";

		//System.out.println("message" + message);
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	
	@GetMapping("/edit")
	public String updateSpecializationData() {
		return "";
	}
	
	@GetMapping("/delete")
	public String deleteSpecializationData(@RequestParam Long Id, RedirectAttributes attributes) {
		spes.removeSpecialization(Id);
		attributes.addAttribute("message", "Record ("+ Id +")is removed");
		return "redirect:all";
	}

}

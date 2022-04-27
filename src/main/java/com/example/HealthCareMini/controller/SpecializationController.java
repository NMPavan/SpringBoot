package com.example.HealthCareMini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HealthCareMini.Entity.Specialization;
import com.example.HealthCareMini.services.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService spes;
	
	@GetMapping("/all")
	public  String getAllSpecializationData(Model model) {
		List<Specialization> spe = spes.getAllSpecializedData();
		model.addAttribute("list", spe);
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

}

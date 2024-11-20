package com.shubh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shubh.entity.CitizenRegistration;
import com.shubh.service.CitizenService;

@Controller
public class CitizenController {

	@Autowired
	private CitizenService citizenService;

	@GetMapping("/") // Load the form with empty form data
	public String index(Model model) {
		model.addAttribute("citizen", new CitizenRegistration());
		return "index"; // Ensure this matches your template name
	}

	@PostMapping("/save-citizen") // Save or update user data
	public String handleSubmitBtn(@ModelAttribute("citizen") CitizenRegistration citizen, Model model) {
		String msg = citizenService.saveOrUdateCitizenReg(citizen); // Call the save/update method
		model.addAttribute("msg", msg);
		model.addAttribute("citizen", new CitizenRegistration()); // Reset form after submission
		return "index"; // Return to the index page
	}

}

package com.shubh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shubh.binding.SearchCriteria;
import com.shubh.entity.CitizenPlan;
import com.shubh.service.CitizenPlanService;

import jakarta.servlet.http.HttpServletResponse;
@Controller
public class CitizenPlanController {
	
	
	@Autowired
	private CitizenPlanService service;

	@GetMapping("/")
	public String index(Model model) {
		formInit(model);
		model.addAttribute("search", new SearchCriteria());
		return "index"; // Returns the view name
	}

	private void formInit(Model model) {
		List<String> planNames = service.getPlanNames();
		List<String> planStatuses = service.getPlanStatus();
		model.addAttribute("planNames", planNames);
		model.addAttribute("planStatuses", planStatuses);
	}

	@PostMapping("/filter-data")
	public String handleSearchBtn(@ModelAttribute("search") SearchCriteria criteria, Model model) {
		List<CitizenPlan> citizenInfo = service.searchCitizens(criteria);
		model.addAttribute("citizens", citizenInfo);
		formInit(model);
		return "index"; // Returns the view name
	}

	@GetMapping("/excel")
	public void downloadExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Dispositon";
		String headerValue = "attachment;filename=date.xls";
		response.addHeader(headerKey, headerValue);
		service.generatedExcel(response);}
   @GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response)  throws Exception {
	   response.setContentType("application/pdf");

		String headerKey = "Content-Dispositon";
		String headerValue = "attachment;filename=date.pdf";
		response.addHeader(headerKey, headerValue);
		service.generatedPdf(response);
		}
		
	
} 

	
	


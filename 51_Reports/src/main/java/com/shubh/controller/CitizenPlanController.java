package com.shubh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.binding.SearchCriteria;
import com.shubh.entity.CitizenPlan;
import com.shubh.service.CitizenPlanService;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class CitizenPlanController {
	
	
	@Autowired
	private CitizenPlanService service;

	@GetMapping("/plan-names")
	public List<String> getPlanNames(Model model) {
		return service.getPlanNames();
	
		 // Returns the view name
	}
	@GetMapping("/plan-status")
	public List<String>etPlanStatus(Model model){
		return service.getPlanStatus();
	}
	
	

	
	@PostMapping("/filter-data")
	public List<CitizenPlan> handleSearchBtn(@RequestBody SearchCriteria criteria) {
	
		return  service.searchCitizens(criteria); // Returns the view name
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

	
	


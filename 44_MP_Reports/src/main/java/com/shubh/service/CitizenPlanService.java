package com.shubh.service;

import java.util.List;

import com.shubh.binding.SearchCriteria;
import com.shubh.entity.CitizenPlan;

import jakarta.servlet.http.HttpServletResponse;

public interface CitizenPlanService {
	public List<String>getPlanNames();
	public List<String>getPlanStatus();
	public List<CitizenPlan>searchCitizens(SearchCriteria criteria);
	public void generatedExcel(HttpServletResponse response) throws Exception;
	public void generatedPdf(HttpServletResponse response) throws Exception;

}

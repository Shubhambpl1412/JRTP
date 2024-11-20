package com.shubh.service;

import java.util.Map;

import com.shubh.binding.Summary;
import com.shubh.binding.Education;
import com.shubh.binding.Income;
import com.shubh.binding.Kids;
import com.shubh.binding.PlanSelection;

public interface DcService {
	
	public Map<Integer, String> getPlanNames();//here we take integer and string bcz we wwant plan name and plan id
	public boolean updatePlanSelection(PlanSelection planSel);
    public boolean saveIncome(Income income);
    public boolean saveKids(Kids kids);
    public boolean saveEducation(Education edu);
    public Summary getSummaryInfo(Integer caseNum);
    
    
    
	
}

package com.shubh.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.binding.Education;
import com.shubh.binding.Income;
import com.shubh.binding.Kids;
import com.shubh.binding.PlanSelection;
import com.shubh.binding.Summary;
import com.shubh.service.DcService;

@RestController
public class DcRestController {
	
	@Autowired
	private DcService dcService;
	
	
	@GetMapping("/plan-names")
	public ResponseEntity<Map<Integer, String>> getPlanNames(){
	Map<Integer, String>planNames=dcService.getPlanNames();
	return new ResponseEntity<>(planNames, HttpStatus.OK);
	
	}
	@PostMapping("/plan-selection")
	public ResponseEntity<String>updatePlanSelection(@RequestBody PlanSelection request){
		boolean status=dcService.updatePlanSelection(request);
		if(status) {
			return new ResponseEntity<>("plan Selection Success",HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>("plan Selection failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/income")
	public ResponseEntity<String>saveIncome(@RequestBody Income income){
		boolean status=dcService.saveIncome(income);
		if(status) {
			return new ResponseEntity<>("Income Selection Success",HttpStatus.CREATED);
			
		}
		else {
			return new ResponseEntity<>("Income selection failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping("/education")
	public ResponseEntity<String>saveEducation(@RequestBody Education education){
		boolean status=dcService.saveEducation(education);
		if(status) {
			return new ResponseEntity<>("Education Selection Success",HttpStatus.CREATED);
			
		}
		else {
			return new ResponseEntity<>("Education selection failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/kids")
	public ResponseEntity<String>saveKids(@RequestBody  Kids kids){
		boolean status=dcService.saveKids(kids);
		if(status) {
			return new ResponseEntity<>("Kids Selection Success",HttpStatus.CREATED);
			
		}
		else {
			return new ResponseEntity<>("kids selection failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/summary/{caseum}")
	public ResponseEntity<Summary>getSummary(@PathVariable Integer caseNum){
		Summary summaryInfo=dcService.getSummaryInfo(caseNum);
		return new ResponseEntity<>(summaryInfo,HttpStatus.OK);
	}
}
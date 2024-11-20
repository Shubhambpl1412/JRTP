package com.shubh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.bindings.EligInfo;
import com.shubh.service.EdService;

@RestController
public class EdRestController {
	
	@Autowired
	private  EdService edService;
	
	
	@GetMapping("/elig/{caseNum}")//take caseNum as path variable
	public ResponseEntity<EligInfo>fetchEligInfo(@PathVariable Integer caseNum){
		EligInfo eligData=edService.determineEligibilty(caseNum);
				return new ResponseEntity<>(eligData,HttpStatus.OK); 
	}
	
	

	  
	
}

package com.shubh.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.shubh.binding.CitizenApp;
import com.shubh.service.AppRegService;

@Controller
public class AppRestController {
	@Autowired
	private AppRegService service;
	
	
	@PostMapping("/app")
	public ResponseEntity<String> createApp(@RequestBody CitizenApp app){
		String respose =service.createCitizenApp(app);
		return new ResponseEntity<>(respose,HttpStatus.OK);
		
	}
	@GetMapping("/applications/{userId}/{userType}")
	public ResponseEntity<List<CitizenApp>> getApplications(@PathVariable("userId") Integer userId,@PathVariable("userType")String userType ){
		List<CitizenApp> apps = service.getApplications(userId, userType);
		return new ResponseEntity<>(apps,HttpStatus.OK);
	}

}

package com.shubh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shubh.binding.CitizenApp;
import com.shubh.entity.CitizenAppEntity;
import com.shubh.repo.AppRepo;


@Service
public class AppRegServiceImpl implements AppRegService {

	private String SSA_API_URL="http://192.3.1:8080/ssn/(ssn)";

	@Autowired
	private AppRepo appRepo;
	
	
	@Override
	public String createCitizenApp(CitizenApp app) {
		Long citizenSsn=app.getCitizenSsn();
		
		CitizenAppEntity appEntity = appRepo.findByCitizenSsn(citizenSsn);
		
		if(appEntity !=null) {
			return "Duplicate Application";
		}
		
		
		//here you can use resttemplate or webtemplate or feign client anything is ok
		RestTemplate rt=new RestTemplate();
		ResponseEntity<String>forEntity=
		rt.getForEntity(SSA_API_URL, String.class,citizenSsn);
		
		
		String body=forEntity.getBody();
		if(body.equals("Rhode Island")){
			CitizenAppEntity entity=new CitizenAppEntity();
			BeanUtils.copyProperties(app, entity);
			CitizenAppEntity save = appRepo.save(entity);
			return "Application Created with Case Number-"+ save.getCaseNum();
		
		}
		return "Invalid SSN";
		}
	@Override
	public List<CitizenApp> getApplications(Integer userId, String userType) {
		List<CitizenAppEntity>entities=null;
		List<CitizenApp>apps=new ArrayList<>();
		if("Admin".equals(userType)) {
			 entities = appRepo.findAll();		
			 }
		else {
			entities=appRepo.findByCreatedBy(userId);
		}
		for(CitizenAppEntity entity:entities) {
			CitizenApp app=new CitizenApp();
			BeanUtils.copyProperties(entity, app);
			apps.add(app);
			}
		
			
		return apps;
		
		
	}
	

}

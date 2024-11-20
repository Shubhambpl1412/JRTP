package com.shubh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubh.entity.CitizenRegistration;
import com.shubh.repo.CitizenRepo;

@Service
public class CitizenServiceImpl implements CitizenService {

	@Autowired
	private CitizenRepo citizenRegRepo;

	@Override
	public String saveOrUdateCitizenReg(CitizenRegistration citizenReg) {
		 citizenRegRepo.save(citizenReg);
         return "Citizen registered successfully!";	}

	@Override
	public List<CitizenRegistration> getCitizenRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}

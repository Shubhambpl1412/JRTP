package com.shubh.service;

import java.util.List;

import com.shubh.entity.CitizenRegistration;


public interface CitizenService {
	
	 public String saveOrUdateCitizenReg(CitizenRegistration citizenReg) ;
	 
     public List<CitizenRegistration>getCitizenRegistrations();
}

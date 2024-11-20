package com.shubh.binding;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

//this class use a response and as well get the request data
@Data
public class CitizenApp {
	
private Integer citizenNum;
	
	private String citizenName;
	
	private String citizenEmail;
	
	private Long citizenPhno;
	
	private String citizenGender;
	
	private LocalDate citizenDob;
	
	private Long citizenSsn;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	private Integer createdBy;


	

}

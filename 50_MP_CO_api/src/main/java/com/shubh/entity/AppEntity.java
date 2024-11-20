package com.shubh.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="CITIZEN_APPS")

public class AppEntity {

@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer caseNum;
	
	private String citizenName;
	
	private String citizenEmail;
	
	private Long phno;
	
	private String gender;
	
	private LocalDate dob;
	
	private Long citizenSsn;
	
	private Integer planId;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	
	@CreationTimestamp
	private LocalDate updatedDate;
}

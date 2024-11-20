package com.shubh.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="IES_APPS")
public class CitizenAppEntity {

@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer caseNum;
	
	private String fullname;
	
	private String citizenEmail;
	
	private Long citizenphno;
	
	private String citizengender;
	
	private LocalDate citizendob;
	
	private Long citizenSsn;
	
	private Integer planId;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	
	@CreationTimestamp
	private LocalDate updatedDate;
}

package com.shubh.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class CitizenPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
	private String name;
	private String email;
	private String gender;
	private Long phno;
	private Long ssn;
	private String planName;
	private String planStatus; LocalDate planStartDate;
	private LocalDate planEndDate;

	// Constructor for initializing fields
	public CitizenPlan(String name, String email, String gender, Long phno, Long ssn,
			String planName, String planStatus, LocalDate planStartDate, LocalDate planEndDate) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.phno = phno;
		this.ssn = ssn;
		this.planName = planName;
		this.planStatus = planStatus;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
	}

	// Default constructor
	public CitizenPlan() {}

}

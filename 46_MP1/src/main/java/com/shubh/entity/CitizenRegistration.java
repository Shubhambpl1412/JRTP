package com.shubh.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CitizenRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fullName;
	private String email;
	private String mobile;
	private String gender;
	private LocalDate dob;
	private Integer ssn;
	
	
	
	public CitizenRegistration(Integer id, String fullName, String email, String mobile, String gender, LocalDate dob,
			Integer ssn) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.dob = dob;
		this.ssn = ssn;
	}
	public CitizenRegistration() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}

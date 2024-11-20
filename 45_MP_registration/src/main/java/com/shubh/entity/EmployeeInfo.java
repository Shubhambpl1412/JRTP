package com.shubh.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EmployeeInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private LocalDate dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	
	
	
	public EmployeeInfo(Integer employeeId, String firstName, String lastName, String email, String phone, LocalDate dob,
			String gender, String country, String state, String city) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.gender = gender;
		this.country = country;
		this.state = state;
		this.city = city;
	}



	public EmployeeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}

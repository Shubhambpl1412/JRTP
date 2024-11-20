package com.shubh.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SignUpRequest {

	private String name;

	private String email;
	private String pwd;
	private String gender;
	private LocalDate dob;
	private long mobile;
	private Integer ssn;
	private String userType;
	private boolean pwdChanged;

}

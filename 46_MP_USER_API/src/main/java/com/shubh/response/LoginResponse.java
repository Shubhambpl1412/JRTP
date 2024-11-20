package com.shubh.response;

import lombok.Data;

@Data
public class LoginResponse {

	private Integer userid;
	private String name;
	private String userType;
	private DashboardResponse dashboard;
	private boolean isValidLogin;
	private boolean pwdChanged;
	
}

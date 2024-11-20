package com.shubh.service;

import com.shubh.request.LoginRequest;
import com.shubh.request.PwdChangeRequest;
import com.shubh.request.SignUpRequest;
import com.shubh.response.LoginResponse;
import com.shubh.response.SignUpResponse;

public interface UserService {
	
	
     //in this function we take signUpRequest as input bcz 
	//whatever data we entered into signUpRequest class that takes as a saveuser details
	
	public SignUpResponse saveUser(SignUpRequest request);

	//same here we take loginRequest details as input
	public LoginResponse userLogin(LoginRequest request);

	//same thing here as pwdChangeRequest
	public LoginResponse updatePwd(PwdChangeRequest request);

	
	public boolean recoverPwd(String email);

}

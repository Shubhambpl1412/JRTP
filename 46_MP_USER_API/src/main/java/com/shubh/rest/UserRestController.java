package com.shubh.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.request.LoginRequest;
import com.shubh.request.PwdChangeRequest;
import com.shubh.request.SignUpRequest;
import com.shubh.response.LoginResponse;
import com.shubh.response.SignUpResponse;
import com.shubh.service.UserService;

@RestController
public class UserRestController {
//my restcontroller talk to service
    @Autowired
    private UserService service;

    @PostMapping("/user")
    public ResponseEntity<SignUpResponse> userRegistration(@RequestBody SignUpRequest request) {
    	SignUpResponse response=service.saveUser(request);
        if (response.getSuccessMsg()!=null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        
        } else if(response.getErrorMsg().contains("Duplicate Email")){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        else {
        	return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest request) {
        LoginResponse response = service.userLogin(request);
        if (response.isValidLogin()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/pwd-change")
    public ResponseEntity<LoginResponse> updatePwd(@RequestBody PwdChangeRequest request) {
        LoginResponse login = service.updatePwd(request);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @GetMapping("/recover-pwd/{email}")
    public ResponseEntity<String> recoverPwd(@PathVariable String email) {
        boolean isValid = service.recoverPwd(email);
        if (isValid) {
            return new ResponseEntity<>("Password sent to your email", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Email", HttpStatus.BAD_REQUEST);
        }
    }
}

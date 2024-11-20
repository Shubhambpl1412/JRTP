package com.shubh.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.shubh.entity.UserInfoEntity;
import com.shubh.repo.UserInfoRepo;
import com.shubh.request.LoginRequest;
import com.shubh.request.PwdChangeRequest;
import com.shubh.request.SignUpRequest;
import com.shubh.response.DashboardResponse;
import com.shubh.response.LoginResponse;
import com.shubh.response.SignUpResponse;
import com.shubh.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoRepo userRepo;

    @Autowired
    private EmailUtils emailUtils;

    @Override
    public SignUpResponse saveUser(SignUpRequest request) {
    	 //for duplicate mail->Account already exist with this email
    	//for registration success->registration success
    	//any exception->registration failed
    	
    	
        SignUpResponse response=new SignUpResponse();
            // Unique email validation
           UserInfoEntity user=userRepo.findByEmail(request.getEmail());
           if(user!=null) {
        	   response.setErrorMsg("Duplicate Email");
        	   return response;
           }

            
            // Generate temporary password
            String tempPwd = generateTempPwd();
            request.setPwd(tempPwd);
            request.setPwdChanged(false); // Set to false as it's a new user(first time login)

            
            // Save user record in DB
            UserInfoEntity entity = new UserInfoEntity();
            BeanUtils.copyProperties(request, entity);
            userRepo.save(entity);

            
            // Send email to user with credentials
             String subject = "IES-Account Created";
            String body = "Your temporary password: " + tempPwd;
            boolean isSent =emailUtils.sendEmail(request.getEmail(), subject, body);
            if(isSent) {
            	response.setSuccessMsg("Registration Success");
            	 }
            else {
            	response.setErrorMsg("Registration Failed");
            }
            return response;
    }

    @Override
    public LoginResponse userLogin(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        UserInfoEntity entity = new UserInfoEntity();
        entity.setEmail(request.getEmail());
        entity.setPwd(request.getPwd());
        Example<UserInfoEntity> of = Example.of(entity);
        List<UserInfoEntity> entities = userRepo.findAll(of);

        if (!entities.isEmpty()) {//this line indicate that  when we check in db on the basis of credintial that 
        	                      //that particular credantial is present of not 
            UserInfoEntity user = entities.get(0);
            
            //here we take userId and usertype bcz we want to know that who change pssword first time 
            response.setUserid(user.getUserid());
            response.setUserType(user.getUserType());

            // Check if the password has been changed
            if (user.isPwdChanged()) {
            	//second login
                response.setPwdChanged(true);
                response.setValidLogin(true);

                // Set dashboard data
                DashboardResponse dashboard = new DashboardResponse();
                dashboard.setPlaneCount(6L);
                dashboard.setBenefitAmtTotal(3200.00);
                dashboard.setCitizeneApCnt(1000L);
                dashboard.setCitizeneDnCnt(500L);
                response.setDashboard(dashboard);
            } else {
            	
            	//first login
                response.setPwdChanged(false);
                //if pwd is not changed then it go to pwd change page
                response.setValidLogin(true);
            }
        } else {
            response.setValidLogin(false);
        }
        return response;
    }

    @Override
    public LoginResponse updatePwd(PwdChangeRequest request) {
        LoginResponse response = new LoginResponse();
        //check which user want to change pwd
        Integer userId = request.getUserId();

        try {
            Optional<UserInfoEntity> findById = userRepo.findById(userId);//take user record from db table
            if (findById.isPresent()) {
                UserInfoEntity entity = findById.get();
                entity.setPwd(request.getPwd());
                entity.setPwdChanged(true); // Update to true
                userRepo.save(entity); // Update in DB

                
                //construct dashboard sponse
                response.setUserid(entity.getUserid());
                response.setUserType(entity.getUserType());
                response.setValidLogin(true);
                response.setPwdChanged(true); // Set as boolean

                
               
                DashboardResponse dashboard = new DashboardResponse();
                dashboard.setPlaneCount(6L);
                dashboard.setBenefitAmtTotal(34000.00);
                dashboard.setCitizeneApCnt(10000L);
                dashboard.setCitizeneDnCnt(5000L);
                response.setDashboard(dashboard);
            }
        } catch (Exception e) {
            logger.error("Error updating password for userId: " + userId, e);
            response.setValidLogin(false);
        }
        return response;
    }

    @Override
    public boolean recoverPwd(String email) {
        UserInfoEntity user = userRepo.findByEmail(email);
        if (user == null) {
            return false; // User not found
        }

        // Avoid sending the actual password. Consider sending a reset link or temporary password instead.
        String subjectString = "IES - Recover Password";
        String bodyString = "To reset your password, please contact support.";
        return emailUtils.sendEmail(email, subjectString, bodyString);
    }

    
    //method to generate temp password
    
    public String generateTempPwd() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}

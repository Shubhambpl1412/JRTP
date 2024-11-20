package com.shubh.service;

import java.util.List;

import com.shubh.entity.UserAccount;

public interface UserService {
	
  public String saveOrUdateUserAcc(UserAccount userAcc) ;
  public List<UserAccount>getAllUserAccounts();
  public UserAccount getUserAcc(Integer userId);
  public boolean deleteUserAcc(Integer userId);
  public boolean updateUserAccStatus(Integer userId, String status);
  
}

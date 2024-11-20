package com.shubh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubh.entity.UserAccount;
import com.shubh.repo.UserAccountRepo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAccountRepo userAccRepo;

    @Override
    public String saveOrUdateUserAcc(UserAccount userAcc) {
        // Check if userId is null to determine if it's a new record
        boolean isNewUser = userAcc.getUserId() == null;

        userAccRepo.save(userAcc); // Save the user account

        // Return appropriate message based on whether it's a new user or an update
        return isNewUser ? "User Record Saved" : "User Record Updated";
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccRepo.findAll(); // Return all user accounts
    }

    @Override
    public UserAccount getUserAcc(Integer userId) {
        Optional<UserAccount> findById = userAccRepo.findById(userId);
        return findById.orElse(null); // Return user account or null if not found
    }

    @Override
    public boolean deleteUserAcc(Integer userId) {
        if (userAccRepo.existsById(userId)) {
            userAccRepo.deleteById(userId); // Delete user account if it exists
            return true; // Return true to indicate deletion success
        }
        return false; // Return false if user account does not exist
    }

    @Override
    public boolean updateUserAccStatus(Integer userId, String status) {
        try {
            userAccRepo.updatUserAccStatus(userId, status); // Update user status
            return true; // Return true to indicate success
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return false; // Return false if there was an error
        }
    }
}

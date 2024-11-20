package com.shubh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shubh.entity.EmployeeInfo;
import com.shubh.repo.EmployeeRepo;
import com.shubh.utils.EmailUtils;  // Import your EmailUtils class

import java.security.SecureRandom;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmailUtils emailUtils; // Inject EmailUtils

    @Override
    public String saveOrUpdateEmployeeInfo(EmployeeInfo employee) {
        boolean isNewUser = employee.getEmployeeId() == null;

        if (isNewUser) {
            // Generate temporary password
            String tempPassword = generateTempPassword();
            // Send the temporary password via email
            emailUtils.sendTempPassword(employee.getEmail(), tempPassword);
            // Optionally, you can store the tempPassword or use it as needed
        }

        employeeRepo.save(employee);
        return isNewUser ? "User Record Saved" : "User Record Updated";
    }

    @Override
    public List<EmployeeInfo> getAllEmployees() {
        return employeeRepo.findAll(); // Make sure EmployeeRepo has this method
    }
    
    @Override
    public String generateTempPassword() {
        SecureRandom random = new SecureRandom();
        int length = 8; // Adjust length as needed
        StringBuilder tempPassword = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            // This generates lowercase letters; you may want to include uppercase letters or digits
            tempPassword.append((char) (random.nextInt(26) + 'a')); // Lowercase letters
        }
        
        return tempPassword.toString();
    }
}

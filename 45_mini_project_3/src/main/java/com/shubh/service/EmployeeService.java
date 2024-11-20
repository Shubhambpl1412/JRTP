package com.shubh.service;

import com.shubh.entity.EmployeeInfo;
import java.util.List;

public interface EmployeeService {
    public String saveOrUpdateEmployeeInfo(EmployeeInfo employee);
    public List<EmployeeInfo> getAllEmployees(); // Return a list of EmployeeInfo
    public String generateTempPassword() ;

}

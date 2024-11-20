package com.shubh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shubh.entity.EmployeeInfo;
import com.shubh.service.EmployeeService;
import com.shubh.utils.EmailUtils;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmailUtils emailUtils; // Inject EmailUtils

    @GetMapping("/")  
    public String index(Model model) {
        model.addAttribute("employee", new EmployeeInfo());
        model.addAttribute("employees", employeeService.getAllEmployees()); // Add this line
        return "index"; 
    }

    @PostMapping("/save-user")  
    public String handleSubmitBtn(@ ModelAttribute("employee") EmployeeInfo employee, Model model) {
        String msg = employeeService.saveOrUpdateEmployeeInfo(employee); // Correct method name
        model.addAttribute("msg", msg);
        model.addAttribute("employee", new EmployeeInfo()); // Reset form
        model.addAttribute("employees", employeeService.getAllEmployees()); // Update list
        return "index"; 
    }

    @GetMapping("/test-email")
    public String testEmail(Model model) {
        String tempPassword = employeeService.generateTempPassword(); // Generate a temp password
        emailUtils.sendTempPassword("recipient@example.com", tempPassword); // Use injected emailUtils
        model.addAttribute("msg", "Test email sent!");
        return "index";
    }
}

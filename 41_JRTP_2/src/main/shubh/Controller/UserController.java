package com.shubh.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shubh.entity.UserAccount;
import com.shubh.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService; // Inject the service

    @GetMapping("/")  // Load the form with empty form data
    public String index(Model model) {
        model.addAttribute("user", new UserAccount());
        return "index"; // Ensure this matches your template name
    }

    @PostMapping("/save-user")  // Save or update user data
    public String handleSubmitBtn(@ModelAttribute("user") UserAccount user, Model model) {
        String msg = userService.saveOrUdateUserAcc(user); // Call the save/update method
        model.addAttribute("msg", msg);
        model.addAttribute("user", new UserAccount()); // Reset form after submission
        return "index"; // Return to the index page
    }

    @GetMapping("/users")  // Retrieve user records
    public String getUsers(Model model) {
        List<UserAccount> userList = userService.getAllUserAccounts();
        model.addAttribute("users", userList);
        return "view-users"; // Ensure this matches your users view template
    }

    @GetMapping("/edit") // Edit a particular user based on ID
    public String editUser(@RequestParam("Id") Integer id, Model model) {
        UserAccount userAcc = userService.getUserAcc(id);
        model.addAttribute("user", userAcc); // Load existing user data into the form
        return "index"; // Return to the index page for editing
    }

    @GetMapping("/delete") // Delete a user
    public String deleteUser(@RequestParam("Id") Integer id,Model model) {
        userService.deleteUserAcc(id);
        model.addAttribute("msg","user Record Deleted");
        return "forward:/users"; // Redirect to the users list after deletion
    }

    @GetMapping("/update-status") // Update user status
    public String statusUpdate(@RequestParam("Id") Integer id, @RequestParam("status") String status ,Model model) {
        userService.updateUserAccStatus(id, status);
        if("Y".equals(status)){
        	model.addAttribute("msg","User Account Activated");
        }
        else {
        	model.addAttribute("msg","User Account De-Activated");
        }
        
        return "forward:/users"; // Redirect to the users list after status update
    }
}

package com.shubh.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class UserInfoEntity {


    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    private String name; // Corrected to String for a name
    private String email;
    private String pwd;
    private String gender;
    private LocalDate dob;
    private long mobile;
    private Integer ssn;
    private String userType;
    private boolean pwdChanged; // Changed to boolean

    // Constructor with all fields
    public UserInfoEntity(Integer userid, String name, String email, String pwd, String gender, LocalDate dob,
                          long mobile, Integer ssn, String userType, boolean pwdChanged) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.gender = gender;
        this.dob = dob;
        this.mobile = mobile;
        this.ssn = ssn;
        this.userType = userType;
        this.pwdChanged = pwdChanged; // Now 

    }

    // Default constructor
    public UserInfoEntity() {
    }

    // Method to check if the password has changed
    public boolean isPwdChanged() {
        return pwdChanged; // Directly return the boolean value
    }
}

package com.shubh.entity;

import java.security.Timestamp;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity

@Table(name="CITIZEN_APPS")
@Data
public class CitizenAppEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long caseNum;

	private String citizenName;

	private String citizenEmail;

	private String citizenGender;

	private LocalDate citizenDob;

	private Long citizenPhno;

	private Long citizenSsn;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@CreationTimestamp
	private LocalDate updatedDate;
	
	private Integer createdBy;
	

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	

	
}

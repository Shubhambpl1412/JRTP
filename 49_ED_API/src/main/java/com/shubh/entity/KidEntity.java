package com.shubh.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity 
@Data
public class KidEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kidId;
	
	private String kidName;
	
	private LocalDate kidDob;
	
	
	private Long kidSsn;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	
	@OneToOne
	@JoinColumn(name="case_num")
	private AppEntity app;
	

}

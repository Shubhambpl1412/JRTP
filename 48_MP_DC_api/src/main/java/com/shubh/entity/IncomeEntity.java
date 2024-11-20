package com.shubh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class IncomeEntity {
	
	private Integer incomeId;
	
	private Double rentIncome;
	
	private Double salaryIncome;
	
	private Double propertyIncome;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@OneToOne
	@JoinColumn(name="case_num")
	private AppEntity app;
	
	

}

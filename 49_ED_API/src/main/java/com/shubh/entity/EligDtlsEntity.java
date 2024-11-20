package com.shubh.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="ELIG_DTLS")
public class EligDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eligId;
	
	private String planStatus;
	
	private LocalDate planStartdDate;
	
	private LocalDate planEndDate;
	
	private Double benefitAmt;
	
	private String denialReason;
	
	//now to check this eligdtls belong to which
	//which case num
	//which plan id
	//we need to represent this as association mapping
	
	
	//which case num
	@OneToOne
	@JoinColumn(name="case_num")
	private AppEntity app;
	
	//which plan id 
	@OneToOne
	@JoinColumn(name="plan_id")
	private PlanEntity plan;
	
	
	
}

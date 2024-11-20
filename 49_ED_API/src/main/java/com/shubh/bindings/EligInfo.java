package com.shubh.bindings;

import java.time.LocalDate;

import lombok.Data;


@Data
public class EligInfo {
	
	private Integer caseNum;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benfitAmt;
	private String denialRsn;

}

package com.shubh.bindings;

import java.time.LocalDate;

import lombok.Data;


@Data
public class CoInfo {

	
	private String planName;
	private String planStatu;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmt;
	private String denialRsn;
	private LocalDate genDate;
}
 
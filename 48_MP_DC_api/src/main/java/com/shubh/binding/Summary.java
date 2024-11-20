package com.shubh.binding;

import lombok.Data;

@Data
public class Summary {
	
	private Income income;
	
	private Education education;
	
	private Kids kids;
	
	private Integer caseNum;
	
	private String planName;




	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Kids getKids() {
		return kids;
	}

	public void setKids(Kids kids) {
		this.kids = kids;
	}
	

}

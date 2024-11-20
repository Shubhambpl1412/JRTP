package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.entity.IncomeEntity;

public interface IncomeRepo  extends JpaRepository<IncomeEntity,Integer >{
   
	public IncomeEntity findByCaseNum(Integer caseNum);
	
	
	
}

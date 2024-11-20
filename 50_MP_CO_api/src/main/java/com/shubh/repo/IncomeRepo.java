package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shubh.entity.IncomeEntity;

public interface IncomeRepo extends JpaRepository<IncomeEntity, Integer>{
	
	@Query("from IncomeEntity where caseNum=:caseNum")
	public IncomeEntity findByCaseNum(Long caseNum);

}

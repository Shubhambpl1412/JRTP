package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shubh.entity.EligDtlsEntity;

public interface EligDtlsRepo extends JpaRepository<EligDtlsEntity, Integer> {

	@Query("from EligDtlsEntity where caseNum=:caseNum")
	public EligDtlsEntity fetchByCaseNum(Integer caseNum);
}

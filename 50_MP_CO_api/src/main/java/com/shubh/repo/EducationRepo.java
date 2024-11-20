package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shubh.entity.EducationEntity;

public interface EducationRepo extends JpaRepository<EducationEntity, Integer> {
  
	public EducationEntity findByCaseNum(Integer caseNum);

	@Query("from EducationEntity where caseNum=:caseNum")
	public EducationEntity findByCaseNum(Long caseNum);
}

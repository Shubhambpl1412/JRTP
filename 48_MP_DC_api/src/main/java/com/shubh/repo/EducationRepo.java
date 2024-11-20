package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.binding.Education;

public interface EducationRepo extends JpaRepository<Education, Integer>{

	
	public Education findByCaseNum(Integer caseNum);
}

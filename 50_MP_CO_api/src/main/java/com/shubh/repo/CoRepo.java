package com.shubh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shubh.entity.CoEntity;

public interface CoRepo extends JpaRepository<CoEntity, Integer>{
 
	@Query("from CoEntity where caseNum =:caseNum")
	public List<CoEntity>fetchByCaseNum(Integer caseNum);
	
}

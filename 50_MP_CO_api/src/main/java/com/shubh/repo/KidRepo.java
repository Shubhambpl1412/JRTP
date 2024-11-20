package com.shubh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shubh.entity.KidEntity;

public interface KidRepo extends JpaRepository<KidEntity, Integer> {
	

	@Query("from KidEntity where caseNum=:caseNum")
	public List<KidEntity> findByCaseNum(Integer caseNum);

}

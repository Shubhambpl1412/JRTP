package com.shubh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.entity.CitizenAppEntity;

public interface AppRepo extends JpaRepository<CitizenAppEntity,Integer >{

	public CitizenAppEntity findByCitizenSsn(Long Ssn);
	
	public List<CitizenAppEntity>findByCreatedBy(Integer userId);
}

package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.entity.UserInfoEntity;


public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Integer>{
	
	UserInfoEntity findByEmail(String email);

}

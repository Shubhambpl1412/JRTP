package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shubh.entity.UserAccount;

import jakarta.transaction.Transactional;

public interface UserAccountRepo  extends JpaRepository<UserAccount, Integer>{
    @Modifying 
    @Transactional
	@Query("update UserAccount set activeSw=:status where userId=:userId")
	public void updatUserAccStatus(Integer userId, String status);
}

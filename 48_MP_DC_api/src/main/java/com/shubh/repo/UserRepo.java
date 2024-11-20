package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity,Integer>{

}

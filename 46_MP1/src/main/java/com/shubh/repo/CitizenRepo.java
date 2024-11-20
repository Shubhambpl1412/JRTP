package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.entity.CitizenRegistration;


public interface CitizenRepo extends JpaRepository<CitizenRegistration, Integer>{

}

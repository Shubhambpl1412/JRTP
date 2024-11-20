package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Integer> {

}

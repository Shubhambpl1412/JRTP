package com.shubh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.entity.KidEntity;

public interface KidRepo extends JpaRepository<KidEntity, Integer> {

public List<KidEntity>findByCaseNum(Integer caseNum);
}

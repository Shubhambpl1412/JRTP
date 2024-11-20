package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubh.entity.EmployeeInfo;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeInfo, Integer> {

}

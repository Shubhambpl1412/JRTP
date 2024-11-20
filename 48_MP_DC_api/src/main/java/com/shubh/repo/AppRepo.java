package com.shubh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.entity.AppEntity;

public interface AppRepo  extends JpaRepository<AppEntity, Integer>{

}

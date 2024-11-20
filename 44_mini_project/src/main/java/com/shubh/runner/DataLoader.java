package com.shubh.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

import com.shubh.entity.CitizenPlan;
import com.shubh.repo.CitizenPlanRepo;

public class DataLoader {
	@Autowired
	private CitizenPlanRepo repo;

	public void run(ApplicationArguments args)  {
		CitizenPlan p1 = new CitizenPlan("john", "john@mail", "Male", 898989L, 990L, "Cash", "Approved", LocalDate.now(), LocalDate.now().plusMonths(6));
		CitizenPlan p2 = new CitizenPlan("smith", "smith@mail", "Female", 898989L, 991L, "Cash", "Denied",null,null);
		CitizenPlan p3 = new CitizenPlan("ram", "john@mail", "Male", 898989L, 990L, "Food", "Approved", LocalDate.now(), LocalDate.now().plusMonths(6));
		CitizenPlan p4 = new CitizenPlan("mohan", "smith@mail", "Female", 898989L, 991L, "Food", "Denied",null,null);
		CitizenPlan p5 = new CitizenPlan("nike", "john@mail", "Male", 898989L, 990L, "Medical", "Approved", LocalDate.now(), LocalDate.now().plusMonths(6));
		CitizenPlan p6 = new CitizenPlan("hook", "smith@mail", "Female", 898989L, 991L, "Medical", "Denied",null,null);
	List<CitizenPlan> records = Arrays.asList(p1,p2,p3,p4,p5,p6);
	repo.saveAll(records);
	}

}

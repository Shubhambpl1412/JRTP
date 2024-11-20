package com.shubh.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.condition.ConditionsReportEndpoint;
import org.springframework.stereotype.Service;

import com.shubh.bindings.EligInfo;
import com.shubh.entity.AppEntity;
import com.shubh.entity.CoEntity;
import com.shubh.entity.EducationEntity;
import com.shubh.entity.IncomeEntity;
import com.shubh.entity.KidEntity;
import com.shubh.entity.PlanEntity;
import com.shubh.repo.AppRepo;
import com.shubh.repo.CoRepo;
import com.shubh.repo.EducationRepo;
import com.shubh.repo.IncomeRepo;
import com.shubh.repo.KidRepo;
import com.shubh.repo.PlanRepo;

import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Service
@Data
public class EdServiceImpl implements EdService{
  
	
	@Autowired
	private AppRepo  appRepo;
	
	@Autowired
	private EdService edService;
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private IncomeRepo incomeRepo;
	
	@Autowired
	private KidRepo kidRepo;
	
	@Autowired
	private EducationRepo eduRepo;
	
	@Autowired
	private CoRepo coRepo;
	
	@Override
	public EligInfo determineEligibilty(Integer caseNum) {
		//fetch required info
		//check conditions
		//construct response
		
		
		//here we are getting appdata
		//plan data
		//income data
		EligInfo eligInfo=new EligInfo();
		AppEntity appEntity=appRepo.findById(caseNum).get();
		Integer planId=appEntity.getPlanId();
		PlanEntity planEntity=planRepo.findById(planId).get();
		String planName=planEntity.getPlanName();
		
		eligInfo.setPlanName(planName);
		eligInfo.setCaseNum(caseNum);
		
		IncomeEntity incomeInfo=incomeRepo.findByCaseNum(caseNum);
		
		
		if(planName.equals("SNAP")) {
			if(planName.equals("SNAP")) {
				if(incomeRepo.getSalaryIncome()<=300) {
					eligInfo.setPlanStatus("Approved");
				}
				else {
					eligInfo.setPlanStatus("Denied");
					eligInfo.setDenialRsn("High Income");
				}
			
		
		}
		else if(planName.equals("CCAP")) {
			}
		Double income=incomeInfo.getSalaryIncome();
		List<KidEntity>kids=KidRepo.findByCaseNum(caseNum);
		boolean isValid =true;
		for(KidEntity kid:kids) {
			LocalDate kidDob=kid.getKidDob();
			int year=Period.between(kidDob, LocalDate.now()).getYears();
			if(year>16) {
				isValid=false;
				break;
			}
		}
		if(isValid && income <=300 && !kids.isEmpty()) {
		   eligInfo.setPlanStatus("Approved");}
		   else {
			   eligInfo.setPlanStatus("Denied");
			   
		   }
		if(!isValid) {
			eligInfo.setDenialRsn("Kid age above 16");
		}
		if(income>300) {
			eligInfo.setDenialRsn("High Income");
			
		}
		
		else if(planName.equals("Medicaid")) {
		    Double income=incomeInfo.getSalaryIncome();
		    Double propertyIncome=incomeInfo.getPropertyIncome();
		    Double rentIncome=incomeInfo.getRentIncome();
			
		  if(income<=300 && (propertyIncome+rentIncome)<=0) {
			  eligInfo.setPlanStatus("Approved");
		  }
		  else {
			  eligInfo.setPlanStatus("Denial");
			  eligInfo.setDenialRsn("high Income");
		  }
		    
		}
		else if(planName.equals("medicare")) {
			//get dob of person
			LocalDate dob = appEntity.getDob();
		   int years=Period.between(dob, LocalDate.now()).getYears();
		
			if(years>=65) {
				eligInfo.setPlanStatus("Approved");
			}
			else {
				eligInfo.setPlanStatus("Denial");
				eligInfo.setDenialRsn("Age is not matched");
			}
			
		}
		
		else if(planName.equals("RIW")) {
			EducationEntity edu=eduRepo.findByCaseNum(caseNum);
			Integer graduationYear=edu.getGraduationYear();
			if(graduationYear !=null && incomeInfo==null) {
				eligInfo.setPlanStatus("Approved");
			}
			else {
				eligInfo.setPlanStatus("Denial");
			}
			if(graduationYear==null) {
				eligInfo.setDenialRsn("under graduate");
			}
			if(income!=null) {
				eligInfo.setDenialRsn("already Employee");
			}
		}
		
		//to time period plan will be eligible
		
		if(eligInfo.getPlanStatus().equals("Approved")) {
			eligInfo.setPlanStartDate(LocalDate.now());
			eligInfo.setPlanEndDate(LocalDate.now().plusMonths(6));
			eligInfo.setBenfitAmt(2000.00);
		}
		GenerateCorrespondence(appEntity);
		return eligInfo;
	}

	}




	private void GenerateCorrespondence(AppEntity app) {
      CoEntity entity=new CoEntity();
      entity.setNoticeStatus("pending");
      entity.setApp(app);
      coRepo.save(entity);
	}
}

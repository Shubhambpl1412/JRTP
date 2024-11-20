package com.shubh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubh.binding.Summary;
import com.shubh.binding.Education;
import com.shubh.binding.Income;
import com.shubh.binding.Kid;
import com.shubh.binding.Kids;
import com.shubh.binding.PlanSelection;
import com.shubh.binding.Summary;

import com.shubh.entity.EducationEntity;
import com.shubh.entity.IncomeEntity;
import com.shubh.entity.KidEntity;
import com.shubh.entity.PlanEntity;
import com.shubh.entity.UserEntity;
import com.shubh.repo.AppRepo;
import com.shubh.repo.EducationRepo;
import com.shubh.repo.IncomeRepo;
import com.shubh.repo.KidRepo;
import com.shubh.repo.PlanRepo;
@Service
public class DcServiceImpl implements DcService {

	@Autowired
	private PlanRepo planRepo;
	
	
	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private IncomeRepo incomeRepo;
	
	@Autowired
	private EducationRepo eduRepo;
	
	@Autowired
	private KidRepo kidRepo;
	
	
	
	@Override
	public Map<Integer, String> getPlanNames() {
	List<PlanEntity>plans=planRepo.findAll();
	Map<Integer, String>plansMap =new HashMap<>();
	for(PlanEntity entity: plans) {
		plansMap.put(entity.getPlanId(), entity.getPlanName());
	}
		
		return plansMap;
	}

	@Override
	public boolean updatePlanSelection(PlanSelection planSel) {
		Integer caseNum=planSel.getCaseNum();
		Optional<AppEntity>findById=appRepo.findById(caseNum);
		UserEntity userEntity=userRepo.findById(planSel.getUserId()).get();
		if(findById.isPresent()) {
			AppEntity appEntity =findById.get();
			appEntity.setPlanId(planSel.getPlanId());
			
			appEntity.setUser(userEntity);
			appRepo.save(appEntity);
			return true;
			}
		return false;
	}

	@Override
	public boolean saveIncome(Income income) {
		
		IncomeEntity entity=new IncomeEntity();
		BeanUtils.copyProperties(income, entity);
		
		Integer caseNum=income.getCaseNum();
		Integer userId=income.getUserId();
		
		AppEntity appEntity =appRepo.findBy(caseNum).get();
		UserEntity userEntity =userRepo.findById(userId).get();
		
		entity.setApp(appEntity);
		entity.setUser(userEntity);
		
		incomeRepo.save(entity);
		return true;
	}

	@Override
	public boolean saveKids(Kids kids) {
		
		Integer caseNum=kids.getCaseNum();
		Integer userId=kids.getUserId(); 
		
		AppEntity appEntity=appRepo.findById(caseNum).get();
		UserEntity userEntity=userRepo.findById(userId.get());
		
		
	List<kid>kidsList=kids.getKidsList();
	
	for(Kid kid:kidsList) {
		DcKidEntity entity=new KidEntity();
		BeanUtils.copyProperties(kid, entity);
		
		entity.setApp(appEntity);
		entity.setUser(userEntity);
		
		
	KidRepo.save(entity);
		
	}
		return true;
		
		
		
		
		
	}

	@Override
	public boolean saveEducation(Education edu) {
		
		
		Integer caseNum=edu.getCaseNum();
		Integer userId=edu.getUserId();
		
		AppEntity appEntity =appRepo.findBy(caseNum).get();
		UserEntity userEntity =userRepo.findById(userId).get();
		
		
		EducationEntity entity=new EducationEntity();
		BeanUtils.copyProperties(edu, entity);
		
		entity.setApp(appEntity);
		entity.setUser(userEntity);
		
		eduRepo.save(entity);
		return true;
	}

	@Override
	public Summary getSummaryInfo(Integer caseNum) {
		
		DcSummary summary=new DcSummary();
		
		AppEntity appEntity= appRepo.findById(caseNum).get();
		
		
	   PlanEntity planMasterEntity=	planRepo.findById(appEntity.getPlanId()).get();
		
	   IncomeEntity incomeEntity=incomeRepo.findByCaseNum(caseNum);
	    
	   EducationEntity byCaseNum = eduRepo.findByCaseNum(caseNum);
	   
	   List<KidEntity> kidEntities = kidRepo.findByCaseNum(caseNum);
	   
	   
	   summary.setCaseNum(caseNum);
	   summary.setPlanName(PlanEntity.getPlanName());
	   
	   Income income=new Income();
	   BeanUtils.copyProperties(incomeEntity, income);
	   summary.setIncome(income);
	   
	   Education edu=new Education();
	   BeanUtils.copyProperties(educationEntity, edu);
	   summary.setEducation(edu);
	   
	   
	List<Kid>list=   new ArrayList<>();
	
	for(KidEntity entity:kidEntities) {
		Kid kid= new Kid();
		BeanUtils.copyProperties(entity, kid);
		list.add(kid);
		
	}
	
	   Kids kids=new Kids();
	   
	   kids.setKidsList(list);
	   
	   summary.setKids(null);
	   return summary;
	}

}

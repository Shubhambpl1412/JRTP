package com.shubh.service;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.shubh.bindings.CoInfo;
import com.shubh.bindings.EligInfo;
import com.shubh.entity.AppEntity;
import com.shubh.entity.CoEntity;
import com.shubh.entity.EligDtlsEntity;
import com.shubh.repo.AppRepo;
import com.shubh.repo.CoRepo;
import com.shubh.repo.EligDtlsRepo;
import com.shubh.utils.EmailUtils;
import com.shubh.utils.PdfUtils;
import com.shubh.utils.S3Utils;

@Service
public class CoServiceImpl  implements CoService{

	@Autowired
	private CoRepo coRepo;

	@Autowired
	private EligDtlsRepo edRepo;
	
	@Autowired
	private PdfUtils pdfUtils;
	
	@Autowired
	private S3Utils s3Utils;

	@Autowired
	private EmailUtils emailUtils;
	
	
	@Autowired
	private AppRepo appRepo;
	
	


	@Override
	public List<CoInfo> getNotices(Integer caseNum, String status) {
		List<CoInfo> records = new ArrayList();
		
		List<CoEntity> entities = coRepo.fetchByCaseNum(caseNum);
		
		
		for(CoEntity co:entities) {
			CoInfo info=new CoInfo();
		    BeanUtils.copyProperties(co, info);
		    records.add(info);
			}
		return records;
	}

	@Override
	public boolean printNotice(Integer noticeId) {
		
		CoEntity coEntity=coRepo.findById(noticeId).get();
		Integer caseNum =coEntity.getApp().getCaseNum();
		AppEntity appEntity =appRepo.findById(caseNum).get();
     EligDtlsEntity eligDtlsEntity = edRepo.fetchByCaseNum(caseNum);
	EligDtlsEntity fetchByCaseNum = edRepo.fetchByCaseNum(caseNum);
	EligInfo eligInfo =new EligInfo();
	BeanUtils.copyProperties(eligDtlsEntity, eligInfo);
	File generatePdf=pdfUtils.generatePdf(eligInfo);
	String objUrl=s3Utils.uploadObject(generatePdf);
	
	coEntity.setNoticeS3Url(objUrl);
	coEntity.setNoticePrintDate(LocalDate.now());
	coEntity.setNoticeStatus("History");
	
	coRepo.save(coEntity);
	
	String to=appEntity.getCitizenEmail();
	String subject="";
	String body="";
	
	emailUtils.sendEmail(to, subject, body, generatePdf);
	return true;

	}

	

}

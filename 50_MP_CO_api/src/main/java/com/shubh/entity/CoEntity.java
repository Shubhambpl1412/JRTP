package com.shubh.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="CO_NOTICES")
public class CoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coid;
	
	private  String noticeStatus;
	
	private LocalDate noticePrintDate;
	
	private  String noticeS3Url;
	
	
	//this notice belong to which case num
	@OneToOne
	@JoinColumn(name="case_num")
	private AppEntity app;
	
	
	
	

}

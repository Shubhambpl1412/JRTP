package com.shubh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class EducationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eduId;

	private String highestDegree;

	private Integer graduationYear;

	private String uniName;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;

	@OneToOne
	@JoinColumn(name="case_num")
	private AppEntity app;
	

}

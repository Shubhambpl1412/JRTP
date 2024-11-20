package com.shubh.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class EducationEntity {

	private String highestDegree;
	private Integer graduationYear;
	private String uniName;
}

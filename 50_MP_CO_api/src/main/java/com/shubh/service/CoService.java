package com.shubh.service;

import java.util.List;

import com.shubh.bindings.CoInfo;

public interface CoService {
	
	//get the notices
public List<CoInfo>getNotices(Integer caseNum, String status );

//print the notices
public boolean printNotice(Integer noticeId);

}

package com.shubh.utils;

import java.io.File;

import org.springframework.stereotype.Component;

@Component

public class EmailUtils {

	
	public boolean sendEmail(String to, String subject, String body, File f) {
		// logic to send email with attachment
		
		boolean isSent=false;
		
		return isSent;
	}

}

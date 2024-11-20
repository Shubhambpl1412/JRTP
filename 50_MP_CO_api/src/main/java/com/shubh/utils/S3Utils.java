package com.shubh.utils;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class S3Utils {
	
	@Value("${bucketName}")
	private String bucketName;

	private final AmazonS3 s3;

	public S3Util(AmazonS3 s3) {
		this.s3 = s3;
	

		
		}
	public String uploadObject(File f) {
		s3.putObject(bucketName,f.getName(),f);
		URL url=s3.getUrl(bucketName,f.getName());
		return url.toExternalForm();
	}

		
	

}

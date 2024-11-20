package com.shubh.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

	@Value("${accessKey}")
	private String accessKey;

	@Value("${secret}")
	private String secret;

	@Value("${region}")
	private String region;

	@Bean
	public AmazonS3 s3() {

		AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secret);

		return AmazonS3ClientBuilder.standard()
									.withRegion(region)
									.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
									.build();
}

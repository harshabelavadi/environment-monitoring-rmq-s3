package com.philips.dataprocessing.services;

import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.philips.dataprocessing.Constants.StringConstants;
import com.philips.dataprocessing.models.PublishS3Data;

public class S3Utility {
	
	private String bucketName;
	private BasicAWSCredentials awsCreds;
	private AmazonS3 s3Client;
	private PublishS3Data s3MetaData;
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	
	public void Authorize() {
        bucketName = StringConstants.S3BUCKET.get();
        awsCreds = new BasicAWSCredentials(StringConstants.S3ACCESSKEY.get(), StringConstants.S3SECRETACCESSKEY.get());
        
        try {
            s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion(Regions.DEFAULT_REGION)
                    .build();

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
	}
	
	public void createBucket() {
		if (!s3Client.doesBucketExistV2(bucketName)) {
            s3Client.createBucket(new CreateBucketRequest(bucketName));
        }
	}
	
	public void uploadDataToS3(String uuid, String value) {
		// Upload a text string as a new object.
        s3Client.putObject(bucketName, uuid, value);
        setS3Data(uuid);
	}

	private void setS3Data(String uuid) {
		s3MetaData = new PublishS3Data();
		s3MetaData.setPublishedAt(new Date());
		s3MetaData.setUuid(uuid);
		s3MetaData.setBucketName(bucketName);
		makeApiCalls();
	}

	private void makeApiCalls() {
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();	
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate.exchange("http://localhost:8082/api/monitor/queue/", HttpMethod.POST, new HttpEntity<PublishS3Data>(s3MetaData, headers), PublishS3Data.class);
	}
			
}

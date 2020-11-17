package com.philips.receiver.services;

import java.io.IOException;
import org.json.JSONObject;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;
import com.philips.receiver.Constants.StringConstants;

public class S3Utility {
	private BasicAWSCredentials awsCreds;
	private AmazonS3 s3Client;
	private ResponseHeaderOverrides headerOverrides;
	private GetObjectRequest getObjectRequestHeaderOverride;
	private S3Object headerOverrideObject;
	private JSONConverter jsonConverter;
	private JSONObject receiverJsonObject;
	private GetStatusMessage getStatus;
	private String temperatureStatusKey = StringConstants.TEMPERATURE_STATUS.get();
	private String humidityStatusKey = StringConstants.HUMIDITY_STATUS.get();
	private String recordGenAtKey = StringConstants.RECORD_GEN_AT_PARAMNAME.get(); 
	private String temperatureKey = StringConstants.TEMPERATURE_PARAMNAME.get(); ;
	private String humidityKey = StringConstants.HUMIDITY_PARAMNAME.get(); ;
	
	public void Authorize(String bucketName) {
		
        awsCreds = new BasicAWSCredentials(StringConstants.S3ACCESSKEY.get(), StringConstants.S3SECRETACCESSKEY.get());
        
        try {
            setS3Client(AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion(Regions.DEFAULT_REGION)
                    .build());

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
	}

	public void getS3ObjectData(String bucketName, String objKey) {
		jsonConverter = new JSONConverter();
		getStatus = new GetStatusMessage();
		try {
		// Get an entire object, overriding the specified response headers, and print the object's content.
        headerOverrides = new ResponseHeaderOverrides();
        getObjectRequestHeaderOverride = new GetObjectRequest(bucketName, objKey)
                .withResponseHeaders(headerOverrides);        
        headerOverrideObject = s3Client.getObject(getObjectRequestHeaderOverride);
        setReceiverJsonObject(jsonConverter.getJsonObject(headerOverrideObject.getObjectContent()));
        
        getStatus.getReceiverMessage(receiverJsonObject.getInt(temperatureStatusKey), temperatureStatusKey, receiverJsonObject.get(recordGenAtKey).toString(), receiverJsonObject.getDouble(temperatureKey));
        getStatus.getReceiverMessage(receiverJsonObject.getInt(humidityStatusKey), humidityStatusKey, receiverJsonObject.get(recordGenAtKey).toString(), receiverJsonObject.getDouble(humidityKey));
        
        headerOverrideObject.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public AmazonS3 getS3Client() {
		return s3Client;
	}

	public void setS3Client(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	public JSONObject getReceiverJsonObject() {
		return receiverJsonObject;
	}

	public void setReceiverJsonObject(JSONObject receiverJsonObject) {
		this.receiverJsonObject = receiverJsonObject;
	}
}

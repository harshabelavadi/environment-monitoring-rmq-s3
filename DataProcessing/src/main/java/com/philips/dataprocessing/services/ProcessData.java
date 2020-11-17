package com.philips.dataprocessing.services;

import java.util.Date;
import java.util.UUID;

import org.json.JSONObject;

import com.philips.dataprocessing.Constants.StringConstants;
import com.philips.dataprocessing.models.PublishedMessage;

public class ProcessData {

	private StatusChecker statusChecker;
	private S3Utility s3Utility;
	private String uuid;
	private JSONObject s3JsonData;
	
	public void processFileData(PublishedMessage publishedMessage) {
		statusChecker = new StatusChecker();
		s3Utility = new S3Utility();
		
		uuid = UUID.randomUUID().toString();
		publishedMessage.setTemperatureFlag(statusChecker.getStatusType(publishedMessage.getTemperature(), StringConstants.TEMPERATURE.get()));
		publishedMessage.setHumidityFlag(statusChecker.getStatusType(publishedMessage.getHumidity(), StringConstants.HUMIDITY.get()));
		
		setS3JsonData(publishedMessage);
		
		s3Utility.Authorize();
		s3Utility.createBucket();
		s3Utility.uploadDataToS3(uuid, s3JsonData.toString());
	}

	private void setS3JsonData(PublishedMessage publishedMessage) {
		s3JsonData = new JSONObject();
		s3JsonData.put("deviceId", publishedMessage.getDeviceId());
		s3JsonData.put("deviceName", publishedMessage.getDeviceName());
		s3JsonData.put("deviceDesc", publishedMessage.getDeviceDesc());
		s3JsonData.put("deviceRecordGeneratedTime", publishedMessage.getDeviceRecordGeneratedAt());
		s3JsonData.put("dataId", publishedMessage.getMonitorDataId());
		s3JsonData.put("temperature", publishedMessage.getTemperature());
		s3JsonData.put("humidity", publishedMessage.getHumidity());
		s3JsonData.put("recordPublishedInRabbitMqAt", publishedMessage.getPublishedAt());
		s3JsonData.put("temperatureStatus", publishedMessage.getTemperatureFlag());
		s3JsonData.put("humidityStatus", publishedMessage.getHumidityFlag());
		s3JsonData.put("recordConsumedAndPlacedInS3At", new Date());
	}

}

package com.philips.environmentmonitor.services;

import java.util.Date;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.philips.environmentmonitor.Constants.DoubleConstants;
import com.philips.environmentmonitor.Constants.LogMessageConstants;
import com.philips.environmentmonitor.Constants.NumberConstants;
import com.philips.environmentmonitor.Constants.StringConstants;
import com.philips.environmentmonitor.interfaces.ILogs;
import com.philips.environmentmonitor.models.Devices;
import com.philips.environmentmonitor.models.EnvironmentCondition;
import com.philips.environmentmonitor.models.PublishMessage;

public class MonitorCondition {
	
	private final int temperatureMax = NumberConstants.TEMPERATURE_MAX.get();
	private final int temperatureMin = NumberConstants.TEMPERATURE_MIN.get();
	private final int humidityMax = NumberConstants.HUMIDITY_MAX.get();
	private final int humidityMin = NumberConstants.HUMIDITY_MIN.get();
	private final int milliseconds = NumberConstants.MILLISECONDS.get();
	private long monitorDataId;
	
	private String generatedRecordMessage = LogMessageConstants.GENERATED_RECORD.get();
	private final String logspath = StringConstants.LOGSPATH.get();
	
	private final Double decimalScale = DoubleConstants.DECIMAL_SCALE.get();
	
	private ILogs environmentConditionLogger;
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	private ResponseEntity<Devices> deviceResponse;
	private PublishMessage pubMsg;
	private JSONObject envJson;
	private JSONObject deviceJson;
	private ILogs logs = new Logs("monitor.txt");
	
	private Random randomGenerator;
	private Double temperature;
	private Double humidity;
	private long deviceId;
	
	public MonitorCondition() {
	}
	
	public void monitor()
	{
		environmentConditionLogger = new Logs(logspath);
		while(true)
		{	
			sleep(milliseconds);
			generateRecord();			
			
		}
	}
	
	private void generateRecord() {		
		randomGenerator = new Random();
		int temperatureDifference = temperatureMax - temperatureMin;
		int humidityDifference = humidityMax - humidityMin;
		
		temperature = temperatureMin + randomGenerator.nextDouble() * temperatureDifference;
		humidity = humidityMin + randomGenerator.nextDouble() * humidityDifference;
		
		temperature = Math.round(temperature * decimalScale) / decimalScale;
		humidity = Math.round(humidity * decimalScale) / decimalScale;		
			
		makeApiCalls();
	}
	
	private void makeApiCalls() {

		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		try {
		deviceResponse = restTemplate.exchange("http://localhost:8081/api/monitor/devices/"+this.getDeviceId(), HttpMethod.GET, new HttpEntity<Devices>(headers), Devices.class);
		} catch (Exception exception) {
			logs.logToConsole(StringConstants.DEVICES_NOT_FOUND.get());
			logs.logToConsole(StringConstants.WAITING_FOR_DEVICE_RECORD.get());
			return;
		}
		
		setJsonProperties();				
		setMonitorDataId(restTemplate.exchange("http://localhost:8081/api/monitor/condition/", HttpMethod.POST, new HttpEntity<String>(envJson.toString(), headers), EnvironmentCondition.class).getBody().getId());
		setPublishMessageProperties();	
		
		restTemplate.exchange("http://localhost:8081/api/monitor/queue/", HttpMethod.POST, new HttpEntity<PublishMessage>(pubMsg, headers), PublishMessage.class);
		
		environmentConditionLogger.logToConsole(String.format(generatedRecordMessage, new Date().toString(), temperature, humidity, this.getDeviceId()));
	}

	private void setJsonProperties() {
		envJson = new JSONObject();
		deviceJson = new JSONObject();
		
		deviceJson.put("id", deviceResponse.getBody().getId());
		deviceJson.put("label", deviceResponse.getBody().getLabel());
				
		envJson.put("temperature", temperature);
		envJson.put("humidity", humidity);
		envJson.put("devices", deviceJson);
	}

	private void setPublishMessageProperties() {
		pubMsg = new PublishMessage();
		pubMsg.setMonitorDataId(this.getMonitorDataId());
		pubMsg.setDeviceId(this.getDeviceId());
		pubMsg.setDeviceName(deviceResponse.getBody().getLabel());
		pubMsg.setDeviceDesc(deviceResponse.getBody().getDescription());
		pubMsg.setDeviceRecordGeneratedAt(deviceResponse.getBody().getGeneratedAt());
		pubMsg.setTemperature(temperature);
		pubMsg.setHumidity(humidity);
		pubMsg.setPublishedAt(new Date());
		pubMsg.setStatus(String.format(StringConstants.PUBLISHEDMESSAGE.get(), this.getMonitorDataId()));		
	}

	private void sleep(int milliseconds)
	{
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public long getMonitorDataId() {
		return monitorDataId;
	}

	public void setMonitorDataId(long monitorDataId) {
		this.monitorDataId = monitorDataId;
	}

}

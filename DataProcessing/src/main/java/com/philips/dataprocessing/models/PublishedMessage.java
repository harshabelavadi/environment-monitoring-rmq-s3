package com.philips.dataprocessing.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PublishedMessage {
	private long monitorDataId;
	private Date publishedAt;
	private String status;
	private Double temperature;
	private Double humidity;
	private long deviceId;
	private String deviceName;
	private String deviceDesc;
	private Date deviceRecordGeneratedAt;
	private int temperatureFlag;
	private int humidityFlag;

	public long getMonitorDataId() {
		return monitorDataId;
	}

	public void setMonitorDataId(long statusId) {
		this.monitorDataId = statusId;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceDesc() {
		return deviceDesc;
	}

	public void setDeviceDesc(String deviceDesc) {
		this.deviceDesc = deviceDesc;
	}

	public int getTemperatureFlag() {
		return temperatureFlag;
	}

	public void setTemperatureFlag(int temperatureFlag) {
		this.temperatureFlag = temperatureFlag;
	}

	public int getHumidityFlag() {
		return humidityFlag;
	}

	public void setHumidityFlag(int humidityFlag) {
		this.humidityFlag = humidityFlag;
	}	
	
	public Date getDeviceRecordGeneratedAt() {
		return deviceRecordGeneratedAt;
	}

	public void setDeviceRecordGeneratedAt(Date deviceRecordGeneratedAt) {
		this.deviceRecordGeneratedAt = deviceRecordGeneratedAt;
	}	
}

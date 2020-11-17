package com.philips.environmentmonitor.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * This entity class stores temperature and humidity information received by each device
 * in the icu.
 **/

@Entity
@Table(name = "EnvironmentCondition")
public class EnvironmentCondition implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "Id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "GeneratedAt")
	private Date generatedAt;
	
	@Column(name = "Temperature")
	private Double temperature;
	
	@Column(name = "Humidity")
	private Double humidity;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="Id")
	private Devices devices;
	
	
	public EnvironmentCondition() {
	}

	public EnvironmentCondition(long id, Double temperature, Double humidity, Devices devices) {
		this.id = id;
		this.temperature = temperature;
		this.humidity = humidity;
		this.devices = devices;
	}

	public Devices getDevices() {
		return devices;
	}

	public void setDevices(Devices devices) {
		this.devices = devices;
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

	public Long getId() {
		return id;
	}

	@PrePersist
	public void setGeneratedAt() {
	    this.generatedAt = new Date();
	}
	
	public Date getGeneratedAt() {
		return this.generatedAt;
	}
	
}

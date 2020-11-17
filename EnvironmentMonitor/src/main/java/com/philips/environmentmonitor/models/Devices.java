package com.philips.environmentmonitor.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * This Devices class is an entity with 
 * id , label, description. This entity stores information on 
 * all devices in the Icu.
 **/

@Entity
@Table(name = "Devices")
public class Devices implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "Id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "GeneratedAt")
	private Date generatedAt;
	
	@Column(name = "Label")
	private String label;
	
	@Column(name = "Description")
	private String description;
	
	public Devices() {
	}

	public Devices(long id, String label, String description) {
		this.id = id;
		this.label = label;
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
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

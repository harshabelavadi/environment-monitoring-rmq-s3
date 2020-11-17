package com.philips.environmentmonitor.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.philips.environmentmonitor.exception.ResourceNotFoundException;
import com.philips.environmentmonitor.models.Devices;
import com.philips.environmentmonitor.repositories.DevicesRepository;

@RestController
@RequestMapping("/api/monitor/")
public class DevicesController {

	@Autowired
	private DevicesRepository devicesRepository;
	
	// get devices
	@GetMapping("devices")
	public List<Devices> getAllDevices() {
		return this.devicesRepository.findAll();
	}
	
	// get devices by id
	@GetMapping("devices/{id}")
	public ResponseEntity<Devices> getDeviceById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		Devices device = devicesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Device record not found for this id ::" + id));
		return ResponseEntity.ok().body(device);
	}

	// save devices record
	@PostMapping("devices")
	public Devices createDevice(@RequestBody Devices device) {
		return this.devicesRepository.save(device);
	}
	
	// update devices record
	@PutMapping("devices/{id}")
	public ResponseEntity<Devices> updateDevice(@PathVariable(value = "id") Long id, 
			@Valid @RequestBody Devices deviceRecord) throws ResourceNotFoundException {
		Devices device = devicesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Device record not found for this id ::" + id));
		
		device.setLabel(deviceRecord.getLabel());
		device.setDescription(deviceRecord.getDescription());
		
		return ResponseEntity.ok(this.devicesRepository.save(device));
	}
	
	// delete devices record
	@DeleteMapping("devices/{id}")
	public Map<String, Boolean> deleteDevice(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Map<String, Boolean> response = new HashMap<>();
		Devices device = devicesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Device record not found for this id ::" + id));
		this.devicesRepository.delete(device);
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
}

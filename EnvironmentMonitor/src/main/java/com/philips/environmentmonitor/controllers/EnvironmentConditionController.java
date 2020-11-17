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
import com.philips.environmentmonitor.models.EnvironmentCondition;
import com.philips.environmentmonitor.repositories.EnvironmentConditionRepository;

@RestController
@RequestMapping("/api/monitor/")
public class EnvironmentConditionController {
	@Autowired
	private EnvironmentConditionRepository environmentConditionRepository;
	
	// get environment condition
	@GetMapping("condition")
	public List<EnvironmentCondition> getAllRecords() {
		return this.environmentConditionRepository.findAll();
	}

	// get environment condition by id
	@GetMapping("condition/{id}")
	public ResponseEntity<EnvironmentCondition> getConditionById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		EnvironmentCondition environmentCondition = environmentConditionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Device record not found for this id ::" + id));
		return ResponseEntity.ok().body(environmentCondition);
	}

	//	save environment condition record
	@PostMapping("condition")
	public EnvironmentCondition createCondition(@RequestBody EnvironmentCondition environmentCondition) {
		return this.environmentConditionRepository.save(environmentCondition);
	}
	
	// update environment condition record
	@PutMapping("condition/{id}")
	public ResponseEntity<EnvironmentCondition> updateCondition(@PathVariable(value = "id") Long id, 
			@Valid @RequestBody EnvironmentCondition environmentConditionRecord) throws ResourceNotFoundException {
		EnvironmentCondition environmentCondition = environmentConditionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Device record not found for this id ::" + id));
		
		environmentCondition.setHumidity(environmentConditionRecord.getHumidity());
		environmentCondition.setTemperature(environmentConditionRecord.getTemperature());
		
		return ResponseEntity.ok(this.environmentConditionRepository.save(environmentCondition));
	}
	
	// delete environment condition record
	@DeleteMapping("condition/{id}")
	public Map<String, Boolean> deleteCondition(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Map<String, Boolean> response = new HashMap<>();
		EnvironmentCondition environmentCondition = environmentConditionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Device record not found for this id ::" + id));
		this.environmentConditionRepository.delete(environmentCondition);
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
}

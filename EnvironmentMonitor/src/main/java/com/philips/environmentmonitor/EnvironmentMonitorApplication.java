package com.philips.environmentmonitor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.philips.environmentmonitor.services.MonitorCondition;

@SpringBootApplication
public class EnvironmentMonitorApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(EnvironmentMonitorApplication.class, args);
		Executor executor = Executors.newFixedThreadPool(4);
		
		for (int i = 1; i <= 4; i++) {
	        final int id = i;
	        executor.execute(() -> {
	        	MonitorCondition monitorCondition = new MonitorCondition();
	        	monitorCondition.setDeviceId(id);
	        	monitorCondition.monitor();
	        });
		}	
		
	}

}

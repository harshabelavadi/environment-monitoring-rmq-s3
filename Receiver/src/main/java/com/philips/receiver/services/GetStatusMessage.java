package com.philips.receiver.services;

import java.util.Date;

import com.philips.receiver.Constants.StringConstants;
import com.philips.receiver.interfaces.ILogs;

public class GetStatusMessage {
	
	private String message = StringConstants.EMPTY.get();
	private ILogs logger;
	
	public void getReceiverMessage(int status, String paramName, String recordGenAt, Double parmValue) {
		logger = new Logs(StringConstants.LOGSPATH.get());		
		if (paramName == StringConstants.TEMPERATURE_STATUS.get()) {
			setMessage(String.format(StringConstants.RECORD_GEN_AT.get() + " : " + getTemperatureStatus(status) + " : "+ StringConstants.RECORD_RECEIVED_AT.get() +"\n", recordGenAt, parmValue, new Date()));
			logger.logToConsole(message);
			return;
		} 	
		setMessage(String.format(StringConstants.RECORD_GEN_AT.get() + " : " + getHumidityStatus(status) + " : "+ StringConstants.RECORD_RECEIVED_AT.get() +"\n", recordGenAt, parmValue, new Date()));
		logger.logToConsole(message);	
	}
	
	private String getTemperatureStatus(int status) {
		
		switch(status) {
			case 0 : return StringConstants.TEMP_NORMAL.get();
			case 1 : return StringConstants.ALERT_TEMP_WARNING.get();
			case 2 : return StringConstants.ALERT_TEMP_ERROR.get();
		}
		return null;	
	}
	
	private String getHumidityStatus(int status) {
		
		switch(status) {
			case 0 : return StringConstants.HUMIDITY_NORMAL.get();
			case 1 : return StringConstants.ALERT_HUMIDITY_WARNING.get();
			case 2 : return StringConstants.ALERT_HUMIDITY_ERROR.get();
		}
		return null;	
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

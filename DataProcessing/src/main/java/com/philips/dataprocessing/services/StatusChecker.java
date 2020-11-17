package com.philips.dataprocessing.services;

import com.philips.dataprocessing.Constants.FactorConstants;
import com.philips.dataprocessing.Constants.StringConstants;

public class StatusChecker {
	
	public int getStatusType(Double paramValue, String paramName) {
		if(paramName == StringConstants.TEMPERATURE.get())
			return checkTemperature(paramValue);
		return checkHumidity(paramValue);
	}

	private int checkHumidity(Double paramValue) {
		return (paramValue > FactorConstants.HUMIDITY_WARNLOW.get() && paramValue < FactorConstants.HUMIDITY_WARNHIGH.get()) ? 0 : 
			(paramValue > FactorConstants.HUMIDITY_ERRORLOW.get() && paramValue < FactorConstants.HUMIDITY_ERRORHIGH.get()) ? 1 : 2;
	}

	private int checkTemperature(Double paramValue) {
		return (paramValue > FactorConstants.TEMPERATURE_WARNLOW.get() && paramValue < FactorConstants.TEMPERATURE_WARNHIGH.get()) ? 0 : 
			(paramValue > FactorConstants.TEMPERATURE_ERRORLOW.get() && paramValue < FactorConstants.TEMPERATURE_ERRORHIGH.get()) ? 1 : 2;
	}
}

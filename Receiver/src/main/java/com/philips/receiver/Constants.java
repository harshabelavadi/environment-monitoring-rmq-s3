package com.philips.receiver;

public class Constants {
	
	public enum StringConstants
	{
		EXCHANGE("exchangeMessage"), EMPTY(""),
		S3ACCESSKEY("Your Access Key"),
		S3SECRETACCESSKEY("Your Secret Key"),
		S3QUEUE("s3Queue"), S3ROUTINGKEY("s3RouteKey"),
		ALERT_TEMP_WARNING("TEMPERATURE WARNING! - Temperature = %s"), 
		ALERT_TEMP_ERROR("TEMPERATURE ERROR! - Temperature = %s"),
		ALERT_HUMIDITY_WARNING("HUMIDITY WARNING! - Humidity = %s"), 
		ALERT_HUMIDITY_ERROR("HUMIDITY ERROR! - Humidity = %s"),
		TEMP_NORMAL("TEMPERATURE IS NORMAL - Temperature = %s"), 
		HUMIDITY_NORMAL("HUMIDITY IS NORMAL - Humidity = %s"),
		TEMPERATURE_STATUS("temperatureStatus"), 
		HUMIDITY_STATUS("humidityStatus"), 
		RECORD_GEN_AT("Record generated at %s"),
		RECORD_RECEIVED_AT("Record received at %s"), 
		RECORD_GEN_AT_PARAMNAME("recordPublishedInRabbitMqAt"),
		TEMPERATURE_PARAMNAME("temperature"), 
		HUMIDITY_PARAMNAME("humidity"),
		LOGSPATH("logs.txt");
		
		private final String stringConstant;
		private StringConstants(final String constant)
		{
			this.stringConstant = constant;
		}
		
		public String get()
		{
			return this.stringConstant;
		}
	}

}

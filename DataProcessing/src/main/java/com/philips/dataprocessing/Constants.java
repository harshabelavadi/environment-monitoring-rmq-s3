package com.philips.dataprocessing;

public class Constants {
	
	public enum StringConstants
	{
		QUEUE("monitorQueue"), EXCHANGE("exchangeMessage"), ROUTINGKEY("routeKey"),
		TEMPERATURE("temperature"), HUMIDITY("humidity"),LOGSPATH("logs.txt"),
		S3BUCKET("monitorbucket"), S3ACCESSKEY("Your Access Key"), 
		S3SECRETACCESSKEY("Your Secret Key"), 
		S3QUEUE("s3Queue"), S3ROUTINGKEY("s3RouteKey"),
		CONSUMEDMESSAGE("Message with dataId : %s consumed for processing from queue at: %s ");
		
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
	
	public enum FactorConstants 
	{
		TEMPERATURE_WARNLOW(8.0), TEMPERATURE_WARNHIGH(45.0), TEMPERATURE_ERRORLOW(5.0), TEMPERATURE_ERRORHIGH(50.0),
		HUMIDITY_WARNLOW(15.0), HUMIDITY_WARNHIGH(35.0), HUMIDITY_ERRORLOW(10.0), HUMIDITY_ERRORHIGH(40.0);
		
		private final Double number;
		private FactorConstants(final Double number)
		{
			this.number = number;
		}
		
		public Double get()
		{
			return this.number;
		}
	}
	
}

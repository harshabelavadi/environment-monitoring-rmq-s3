package com.philips.environmentmonitor;

public class Constants {

	public enum StringConstants
	{
		TEXT("text/plain"),	LOGSPATH("logs.txt"), 
		COMMA(","), NEWLINE("\n"), EMPTY(""), QUEUE("monitorQueue"),
		EXCHANGE("exchangeMessage"), ROUTINGKEY("routeKey"),
		PUBLISHEDMESSAGE("The record with monitor data Id : %d is placed in queue and ready to be consumed."),
		DEVICES_NOT_FOUND("Device record not found, please insert device record into \"devices\" table by hitting \"http://localhost:8081/api/monitor/devices/\" post api through postman"),
		WAITING_FOR_DEVICE_RECORD("Waiting for device records to be inserted....");
		
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
	
	public enum LogMessageConstants
	{
		WAITFORSENDER("Waiting for sender to send data.\n"),
		
		NORMALCONDITION("Record recieved at : %s, %s : %s, Condition : Normal\n"),
		WARNING("Record recieved at : %s, %s : %s, Condition : WARNING!\n"),
		ERROR("Record recieved at : %s, %s : %s, Condition : ERROR!\n"),
		
		EMAILALERTWARNING("\nEmail Alert sent to : %s at : %s, %s : %s, Condition : WARNING!\n"),
		EMAILALERTERROR("\nEmail Alert sent to : %s at : %s, %s : %s, Condition : ERROR!\n"),
		SMSALERTWARNING("SMS Alert sent to SMS : %s at : %s, %s : %s, Condition : WARNING!\n\n"),
		SMSALERTERROR("SMS Alert sent to SMS : %s at : %s, %s : %s, Condition : ERROR!\n\n"),
		
		GENERATED_RECORD("Record generated at : %s Temperature : %s, Humidity : %s, Device Id: %s\n"),
		TIMEOUT("The receiver waiting limit exceeded! No response from the sender. Terminating Application!\n"),
		EMAIL_ADDRESS("user@philips.com"), SMS_NUMBER("+91-9488394212");
				
		private final String logMessage;
		private LogMessageConstants(final String constant)
		{
			this.logMessage = constant;
		}
		
		public String get()
		{
			return this.logMessage;
		}
	}
	
	public enum NumberConstants 
	{
		MILLISECONDS(5000), TEMPERATURE_MAX(60), HUMIDITY_MAX(40), TEMPERATURE_MIN(15), HUMIDITY_MIN(10);
		
		private final int number;
		private NumberConstants(final int number)
		{
			this.number = number;
		}
		
		public int get()
		{
			return this.number;
		}
	}
	
	public enum DoubleConstants 
	{
		DECIMAL_SCALE(1000.0);
		
		private final Double number;
		private DoubleConstants(final Double number)
		{
			this.number = number;
		}
		
		public Double get()
		{
			return this.number;
		}
	}
	
}

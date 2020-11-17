package com.philips.environmentmonitor.interfaces;

public interface ILogs {
	public void logToConsole(String message);
	public void invokeAppendOperationToFile(String message);
}

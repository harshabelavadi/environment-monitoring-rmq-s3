package com.philips.receiver.services;

import java.io.FileWriter;
import java.util.logging.Logger;

import com.philips.receiver.filehandler.FileOperations;
import com.philips.receiver.interfaces.IFileOperations;
import com.philips.receiver.interfaces.ILogs;

public class Logs implements ILogs{
	private IFileOperations fileOperations;
	private FileWriter writer;
	private String filePath;
	private Logger log;
	
	public Logs(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void logToConsole(String message) {
		log = Logger.getLogger(Logs.class.getName()); 
		log.info(message);
		invokeAppendOperationToFile(message);
	}

	@Override
	public void invokeAppendOperationToFile(String message) {
		fileOperations = new FileOperations(filePath);
		writer = fileOperations.openWriterStream();
		fileOperations.appendFile(writer, message);
		fileOperations.closeWriter(writer);
	}
}

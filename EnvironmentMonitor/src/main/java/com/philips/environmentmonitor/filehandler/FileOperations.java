package com.philips.environmentmonitor.filehandler;

import java.io.FileWriter;
import java.io.IOException;

import com.philips.environmentmonitor.interfaces.IFileOperations;

public class FileOperations implements IFileOperations{
private String filePath;
	
	public FileOperations(String filePath) {
		this.filePath = filePath;
	}
	

	@Override
	public FileWriter openWriterStream() 
	{
		try {
			FileWriter writer = new FileWriter(this.filePath, true);
			return writer;	
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void appendFile(FileWriter writer, String record) 
	{
		try {
			writer.append(record);
			writer.flush();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public void closeWriter(FileWriter writer)
	{
		try {
			writer.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
}

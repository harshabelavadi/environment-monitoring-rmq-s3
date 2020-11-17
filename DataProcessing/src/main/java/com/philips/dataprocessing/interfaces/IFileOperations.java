package com.philips.dataprocessing.interfaces;

import java.io.FileWriter;

public interface IFileOperations {
	public FileWriter openWriterStream();
	public void appendFile(FileWriter writer, String record);
	public void closeWriter(FileWriter writer);
}

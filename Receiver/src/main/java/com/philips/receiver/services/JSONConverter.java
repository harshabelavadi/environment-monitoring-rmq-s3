package com.philips.receiver.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.philips.receiver.Constants.StringConstants;

public class JSONConverter {
	
	private JSONObject receiverJsonObject;
	private BufferedReader reader;
	private String line;
	private String content;
	
	public JSONObject getJsonObject(InputStream input) {

		// Read the text input stream one line at a time and display each line.
        reader = new BufferedReader(new InputStreamReader(input));
        line = null;
        content = StringConstants.EMPTY.get();
        try {
			while ((line = reader.readLine()) != null) {
				content+=line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        receiverJsonObject = new JSONObject(content);        
		return receiverJsonObject;
    }
}

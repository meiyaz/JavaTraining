package org.utils;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONObject;

/*
 * @author: meiyaz
 */
public class CommonUtils {
	public JSONObject readJSONInput(BufferedReader reader) throws IOException {
		String inputData = "";
		String line = null;
		while((line = reader.readLine())  != null) {
			inputData += line;
		}
		// to print input data
		System.out.print(inputData);
		
		JSONObject json = new JSONObject(inputData);
		return json;
	}
}

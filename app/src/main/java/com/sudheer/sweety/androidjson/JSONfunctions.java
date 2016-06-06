package com.sudheer.sweety.androidjson;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONfunctions {


	public static JSONObject getJSONfromURL(String url) {
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;


		// Download JSON data from URL
		try {
			HttpURLConnection con=null;
			URL u = new URL(" https://dl.dropboxusercontent.com/u/746330/facts.json");
			con = (HttpURLConnection) u.openConnection();
			is = con.getInputStream();

		}	catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}


		// Convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		try {

			jArray = new JSONObject(result);
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		return jArray;
	}

}


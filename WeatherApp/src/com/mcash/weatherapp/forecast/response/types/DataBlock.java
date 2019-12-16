/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response.types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class DataBlock {
    
    private String summary;
    private String icon;
    private ForecastData[] data;
    
    public DataBlock(JSONObject forecastBlockJsonObject) {
            try {
                    summary = forecastBlockJsonObject.getString("summary");
            } catch (JSONException e) {
            }
            try {
                    icon = forecastBlockJsonObject.getString("icon");
            } catch (JSONException e) {
            }
            try {
                    JSONArray blockData = forecastBlockJsonObject.getJSONArray("data");
                    int blockDataLength = blockData.length();
                    data = new ForecastData[blockDataLength];
                    for(int i=0; i<blockDataLength; i++) {
                            ForecastData d = new ForecastData(blockData.getJSONObject(i));
                            data[i] = d;
                    }
            } catch (JSONException e) {
            }
    }
    
    public String getValue(String key) {
		if(key.equals(new String("summary"))) {
			return summary;
		}
		else if(key.equals(new String("icon"))) {
			return icon;
		}
		return null;
	}
	
	/**
	 * method returns the summary for this data block;
	 * @return string value of summary
	 */
	public String summary() {
		return summary;
	}
	
	/**
	 * method returns the icon for this data block;
	 * @return string value of icon
	 */
	public String icon() {
		return icon;
	}
	
	public ForecastData[] data() {
		return data;
	}
    
    
    
    
}

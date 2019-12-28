/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.model;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Alert 
{
    private HashMap<String, Object> dataMap;

    public Alert(JSONObject forecastAlertJsonObject) 
    {
        dataMap = new HashMap<String, Object>();
        JSONArray dataPointNames = forecastAlertJsonObject.names();
        for (int i = 0; i < dataPointNames.length(); i++) 
        {
            String dataPointName = "";
            Object dataPointValue = null;
            
            dataPointName = dataPointNames.getString(i);
            dataPointValue = forecastAlertJsonObject.get(dataPointName);
            
            dataMap.put(dataPointName, dataPointValue);
        }
    }    

    public String getValue(String key) {
        return dataMap.get(key).toString();
    }

    public int getValueAsInt(String key) {
        return Integer.parseInt(getValue(key));
    }

    public Double getValueAsDouble(String key) {
        return Double.parseDouble(getValue(key));
    }

    public Long getValueAsLong(String key) {
        return Long.parseLong(getValue(key));
    }
    
    public void toDisplay()
    {
    	for(String key : dataMap.keySet())
    	{
    		System.out.println(key + "\t" + dataMap.get(key) + "");
    	}
    }
    
    /**
     * alerts components:
     * 	title		String
     * 	time		int
     * 	expires		int
     * 	description	str
     * 	uri			str - url to weather.gov
     */
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.forecast.response.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class ForecastData {

    private HashMap<String, Object> dataMap;

    public ForecastData(JSONObject forecastObject) {
        dataMap = new HashMap<String, Object>();
        JSONArray dataNames = forecastObject.names();

        for (int index = 0; index < dataNames.length(); index++) 
        {
            String dataPointName = "";
            Object dataPointValue = null;
            dataPointName = dataNames.getString(index);
            dataPointValue = forecastObject.get(dataPointName);

            dataMap.put(dataPointName, dataPointValue);            
        }
    }

    public String getValue(String key) {
        return dataMap.get(key).toString();
    }
    
    public void toDisplay()
    {
        for(String v : dataMap.keySet())
        {
        	String key = v;
        	String value = dataMap.get(key).toString();
        	
        	if(key.contains("time") || key.contains("Time"))
        	{
        		Date date = new Date(Integer.parseInt(value) * 1000L);
        		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        		formater.setTimeZone(TimeZone.getTimeZone("UTC"));
        		value = formater.format(date);
        	}
        	
            System.out.println(key + ":\t" + value);
        }
    }
}

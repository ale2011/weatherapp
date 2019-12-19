/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response.types;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Flags {

    private HashMap<String, Object> dataMap;

    public Flags(JSONObject jsonObject) {
        dataMap = new HashMap<String, Object>();
        try 
        {
        	if(jsonObject.has("darksky-unavailable"))
            { 
        		Object darkskyunavailable = jsonObject.get("darksky-unavailable");
        		dataMap.put("darkskyunavailable", darkskyunavailable);                
            }
        	if(jsonObject.has("metno-license"))
        	{
	            Object metnolicense = jsonObject.get("metno-license");
	            dataMap.put("metnolicense", metnolicense);
        	}
	        if(jsonObject.has("units"))
	        {
	            Object units = jsonObject.get("units");
	            dataMap.put("units", units);
	        }
        } 
        catch (JSONException e) 
        {
        	
        }
    }
    
    public String getValue(String key) {
        return dataMap.get(key) + "";
    }
    
    public void toDisplay()
    {
    	for(String key : dataMap.keySet())
    	{
    		System.out.println(key + "\t" + dataMap.get(key) + "");
    	}
    }
}

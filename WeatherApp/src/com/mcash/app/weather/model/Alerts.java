/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Alerts {
    
    private Alert[] alerts;
    
    public Alerts(JSONArray forecastArray) 
    {
		if(forecastArray.length() > 0) 
		{
			alerts = new Alert[forecastArray.length()];
			for(int i=0; i<forecastArray.length(); i++) 
			{
				JSONObject forecastIOAlert = forecastArray.getJSONObject(i);
				Alert alertData = new Alert(forecastIOAlert);
				alerts[i] = alertData;
				
			}
		}
	}
    
    public Alert[] getData() 
    {
		return alerts;
	}
    
    public void toDisplay()
    {
    	for(Alert a : alerts)
    	{
    		System.out.println(a);
    		a.toDisplay();
    	}
    }
    
}

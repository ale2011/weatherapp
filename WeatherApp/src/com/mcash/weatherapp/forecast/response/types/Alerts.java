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
public class Alerts {
    
    private Alert[] alerts;
    
    public Alerts(JSONArray forecastArray) {
		if(forecastArray.length() > 0) {
			alerts = new Alert[forecastArray.length()];
			for(int i=0; i<forecastArray.length(); i++) {
				try {
					JSONObject forecastIOAlert = forecastArray.getJSONObject(i);
					Alert alertData = new Alert(forecastIOAlert);
					alerts[i] = alertData;
				}
				catch(JSONException e) {
				}
			}
		}
	}
    
    public Alert[] getData() {
		return alerts;
	}
    
}
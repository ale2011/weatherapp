/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.forecast.response.model;

import java.util.HashMap;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Currently {

    private ForecastData dataMap;

    public Currently(JSONObject forecastObj) 
    {
        dataMap = buildData(forecastObj);
    }

    public String getValue(String key) 
    {
        return dataMap.getValue(key) + "";
    }

    public ForecastData get() 
    {
        return dataMap;
    }

    public ForecastData buildData(JSONObject currentlyJsonObject) 
    {
        return new ForecastData(currentlyJsonObject);
    }
    
    public void toDisplay()
    {
    	dataMap.toDisplay();
    }
    
    /**
      currently components:
	      "time": 1509993277,
	      "summary": "Drizzle",
	      "icon": "rain",
	      "nearestStormDistance": 0,
	      "precipIntensity": 0.0089,
	      "precipIntensityError": 0.0046,
	      "precipProbability": 0.9,
	      "precipType": "rain",
	      "temperature": 66.1,
	      "apparentTemperature": 66.31,
	      "dewPoint": 60.77,
	      "humidity": 0.83,
	      "pressure": 1010.34,
	      "windSpeed": 5.59,
	      "windGust": 12.03,
	      "windBearing": 246,
	      "cloudCover": 0.7,
	      "uvIndex": 1,
	      "visibility": 9.84,
	      "ozone": 267.44      	
     */

}

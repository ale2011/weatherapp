/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response.types;

import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Hourly {
    private DataBlock dataBlock;
    
    public Hourly(JSONObject forecastHourlyJsonObject) {
		//set data points
		dataBlock = buildForecastIODataBlock(forecastHourlyJsonObject);
	}
    
    /**
	 * method which retrieves a list of ForecastIODataPoints 
	 * @return	a list of ForecastIODataPoints 	
	 */
	public ForecastData[] getData() {
		return dataBlock.data();
	}
	
	/**
	 * method which retrieves a particular value from the data, specified by key
	 * @param key	String parameter which provides the key for what data to provide
	 * @return	returns the String value of the key provided, null if it doesnt exist
	 */
	public String getValue(String key) {
		return dataBlock.getValue(key) + "";
	}
	
	/**
	 * returns a single data block for daily weather
	 * @see ForecastIODataBlock
	 */
	public DataBlock get() {
		return dataBlock;
	}
	
	/**
	 * construct the forecastIODataBlock object
	 * @param forecastDailyJsonObject json object of daily weather
	 * @return ForecastIODataBlock which hold all info for that data block
	 * @see ForecastIODataBlock
	 */
	public DataBlock buildForecastIODataBlock(JSONObject forecastHourlyJsonObject) {
		return new DataBlock(forecastHourlyJsonObject);
	}
}
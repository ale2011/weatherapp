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
public class Minutely {
    
    private DataBlock dataBlock;
	
	/**
	 * constructor method which populates the fields related to the minutely weather conditions
	 * @param forecastMinutelyJsonObject input json object of the minutely conditions
	 */
	public Minutely(JSONObject forecastObj) {
		//set data points
		dataBlock = buildData(forecastObj);
	}
	
	/**
	 * method which retrieves a list of DataPoints 
	 * @return	a list of DataPoints 	
	 */
	public ForecastData[] getData() {
		return dataBlock.data();
	}
	
	/**
	 * method which retrieves a particular value from the data, specified by key
	 * @param key	String parameter which provides the key for what data to provide
	 * @return	returns the String value of the key provided, null if it doesnt exist
	 */
	public String getValue(String k) {
        String time = k.split("-")[0];
        String key = k.split("-")[1];
        String values = "";
        
        if(time.equals("all"))
        {
            for(int index = 0; index < dataBlock.getBlockSize(); index++)
            {
                ForecastData fd = dataBlock.getData(index);
                values += fd.getValue(key).toString() + "|";
            }
        }
        else
        {
            for(int index = 0; index < dataBlock.getBlockSize(); index++)
            {
                ForecastData fd = dataBlock.getData(index);
                if(time.equals(fd.getValue("time").toString()))
                    values += fd.getValue(key).toString() + "|";
            }
        }
        return values;  
	}
	
	/**
	 * returns a single data block for minutely weather
	 * @see DataBlock
	 */
	public DataBlock get() {
		return dataBlock;
	}
	
	/**
	 * construct the DataBlock object
	 * @param forecastDailyJsonObject json object of minutely weather
	 * @return DataBlock which hold all info for that data block
	 * @see DataBlock
	 */
	public DataBlock buildData(JSONObject forecastObj) {
		return new DataBlock(forecastObj);
	}
	
	public void toDisplay()
    {
    	dataBlock.toDisplay();
    }
        
}

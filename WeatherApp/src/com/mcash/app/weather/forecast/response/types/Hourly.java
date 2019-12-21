/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.forecast.response.types;

import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Hourly {
    private DataBlock dataBlock;
    
    public Hourly(JSONObject hourlyJsonObject) {
		//set data points
    	dataBlock = buildData(hourlyJsonObject);
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
	 * @param key	"time-key" String parameter which provides the key for what data to provide
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
	public DataBlock buildData(JSONObject hourlyJsonObject) {
		return new DataBlock(hourlyJsonObject);
	}
	
	public void toDisplay()
    {
    	dataBlock.toDisplay();
    }
}

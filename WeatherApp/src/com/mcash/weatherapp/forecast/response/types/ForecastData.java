/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response.types;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class ForecastData {

    private HashMap<String, String> dataMap;

    public ForecastData(JSONObject forecastObject) {
        dataMap = new HashMap<String, String>();
        JSONArray dataNames = forecastObject.names();

        for (int index = 0; index < dataNames.length(); index++) 
        {
            String dataPointName = "";
            Object dataPointValue = null;
            dataPointName = dataNames.getString(index);
            dataPointValue = forecastObject.get(dataPointName);

            dataMap.put(dataPointName, dataPointValue.toString());            
        }
    }

    public String getValue(String key) {
        return dataMap.get(key).toString();
    }
    
    public void print()
    {
        for(String v : dataMap.keySet())
        {
            System.out.println(v + ":\t" + dataMap.get(v).toString());
        }
    }
}

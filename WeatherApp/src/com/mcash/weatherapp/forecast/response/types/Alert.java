/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response.types;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Alert {

    private HashMap<String, String> dataMap;

    public Alert(JSONObject forecastAlertJsonObject) 
    {
        dataMap = new HashMap<String, String>();
        JSONArray dataPointNames = forecastAlertJsonObject.names();
        for (int i = 0; i < dataPointNames.length(); i++) {
            String dataPointName = "";
            String dataPointValue = "";
            try {
                dataPointName = dataPointNames.getString(i);
            } catch (JSONException e) {
                //go to nextsf
                continue;
            }

            try {
                dataPointValue = forecastAlertJsonObject.getString(dataPointName);
            } catch (JSONException e) {
                //go to next
                continue;
            }
            dataMap.put(dataPointName, dataPointValue);
        }
    }
    

    public String getValue(String key) {
        return dataMap.get(key);
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
}

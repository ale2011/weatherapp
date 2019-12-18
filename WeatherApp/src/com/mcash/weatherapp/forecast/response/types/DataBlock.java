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
public class DataBlock {

    private String summary;
    private String icon;
    private String time;
    private ForecastData[] data;

    public DataBlock(JSONObject jsonObj) {
        try {
            if(jsonObj.has("time"))
                time = jsonObj.get("time").toString();
            
            if(jsonObj.has("summary"))
                summary = jsonObj.getString("summary"); 
            
            if(jsonObj.has("icon"))
                icon = jsonObj.getString("icon");
            
            if(jsonObj.has("data"))
            {
                JSONArray blockData = jsonObj.getJSONArray("data");
                int blockDataLength = blockData.length();
                data = new ForecastData[blockDataLength];
                for (int i = 0; i < blockDataLength; i++) 
                {
                    ForecastData d = new ForecastData(blockData.getJSONObject(i));
                    data[i] = d;
                }
            }
        }
        catch (JSONException e) {
        }
    }

    public String getValue(String key) {
        if (key.equals("summary")) {
            return summary;
        } 
        else if (key.equals("icon")) {
            return icon;
        } 
        else if (key.equals("time"))
        {
            return time;
        } 
        else
        {
            
        }
        
        return null;
    }

    public String summary() {
        return summary;
    }

    public String icon() {
        return icon;
    }

    public ForecastData[] data() {
        return data;
    }

}

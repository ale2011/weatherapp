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
    private ForecastData[] data;

    public DataBlock(JSONObject jsonObj) {
        try {
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
    
    public ForecastData getData(int num)
    {
        if(num < data.length)
            return data[num];
        else
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

    public int getBlockSize() {
        return data.length;
    }
}

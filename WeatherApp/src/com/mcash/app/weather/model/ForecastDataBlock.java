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
public class ForecastDataBlock {

    private String summary;
    private String icon;
    private ForecastData[] data;

    public ForecastDataBlock(JSONObject jsonObj) {
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
    
    public void toDisplay()
    {
    	System.out.println("Summary: " + summary + "\nIcon: " + icon);
    	System.out.println("-----------------");
    	
    	int count = 1;
    	for(ForecastData fd : data)
    	{
    		System.out.println("Block #" + count);
    		fd.toDisplay();
    		count++;
    		System.out.println("-----------------");
    	}
    }
}

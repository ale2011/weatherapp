/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Daily {

    private ForecastDataBlock forecastDataBlock;

    public Daily(JSONObject jsonObject) 
    {
    	forecastDataBlock = buildData(jsonObject);
    }

    public ForecastData[] getData() {
        return forecastDataBlock.data();
    }

    public String getValue(String k) throws ParseException {
        String time = k.split("-")[0];
        String key = k.split("-")[1];
        String values = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        if(time.equals("all"))
        {
            for(int index = 0; index < forecastDataBlock.getBlockSize(); index++)
            {
                ForecastData fd = forecastDataBlock.getData(index);
                long d = Long.parseLong(fd.getValue("time"));
                Date date = new Date(d);                
                values += formatter.format(date) + "\t" + fd.getValue(key).toString() + "\n";
            }
        }
        else
        {
            for(int index = 0; index < forecastDataBlock.getBlockSize(); index++)
            {
                ForecastData fd = forecastDataBlock.getData(index);
                if(time.equals(fd.getValue("time").toString()))
                    values += fd.getValue(key).toString() + "|";
            }
        }
        return values;        
    }

    public ForecastDataBlock get() {
        return forecastDataBlock;
    }

    public ForecastDataBlock buildData(JSONObject dailyJsonObject) {
        return new ForecastDataBlock(dailyJsonObject);
    }
    
    public void toDisplay()
    {
    	
    	forecastDataBlock.toDisplay();
    }
}

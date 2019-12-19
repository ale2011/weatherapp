/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response.types;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Daily {

    private DataBlock dataBlock;

    public Daily(JSONObject jsonObject) 
    {
    	dataBlock = buildData(jsonObject);
    }

    public ForecastData[] getData() {
        return dataBlock.data();
    }

    public String getValue(String k) throws ParseException {
        String time = k.split("-")[0];
        String key = k.split("-")[1];
        String values = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        if(time.equals("all"))
        {
            for(int index = 0; index < dataBlock.getBlockSize(); index++)
            {
                ForecastData fd = dataBlock.getData(index);
                long d = Long.parseLong(fd.getValue("time"));
                Date date = new Date(d);                
                values += formatter.format(date) + "\t" + fd.getValue(key).toString() + "\n";
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

    public DataBlock get() {
        return dataBlock;
    }

    public DataBlock buildData(JSONObject dailyJsonObject) {
        return new DataBlock(dailyJsonObject);
    }
    
    public void toDisplay()
    {
    	
    	dataBlock.toDisplay();
    }
}

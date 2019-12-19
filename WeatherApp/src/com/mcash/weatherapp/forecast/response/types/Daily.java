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

    private DataBlock mDataBlock;

    public Daily(JSONObject jsonObject) {
        //set data points
        mDataBlock = buildForecastData(jsonObject);
    }

    public ForecastData[] getData() {
        return mDataBlock.data();
    }

    public String getValue(String k) throws ParseException {
        String time = k.split("-")[0];
        String key = k.split("-")[1];
        String values = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        if(time.equals("all"))
        {
            for(int index = 0; index < mDataBlock.getBlockSize(); index++)
            {
                ForecastData fd = mDataBlock.getData(index);
                long d = Long.parseLong(fd.getValue("time"));
                Date date = new Date(d);                
                values += formatter.format(date) + "\t" + fd.getValue(key).toString() + "\n";
            }
        }
        else
        {
            for(int index = 0; index < mDataBlock.getBlockSize(); index++)
            {
                ForecastData fd = mDataBlock.getData(index);
                if(time.equals(fd.getValue("time").toString()))
                    values += fd.getValue(key).toString() + "|";
            }
        }
        return values;        
    }

    public DataBlock get() {
        return mDataBlock;
    }

    public DataBlock buildForecastData(JSONObject forecastDailyJsonObject) {
        return new DataBlock(forecastDailyJsonObject);
    }
}

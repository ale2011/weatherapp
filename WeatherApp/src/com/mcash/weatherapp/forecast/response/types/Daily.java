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
public class Daily {

    private DataBlock mDataBlock;

    public Daily(JSONObject jsonObject) {
        //set data points
        mDataBlock = buildForecastData(jsonObject);
    }

    public ForecastData[] getData() {
        return mDataBlock.data();
    }

    public String getValue(String key) {
        return mDataBlock.getValue(key) + "";
    }

    public DataBlock get() {
        return mDataBlock;
    }

    public DataBlock buildForecastData(JSONObject forecastDailyJsonObject) {
        return new DataBlock(forecastDailyJsonObject);
    }
}

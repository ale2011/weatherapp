/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response.types;

import java.util.HashMap;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Currently {

    private ForecastData dataMap;

    public Currently(JSONObject forecastObj) {
        dataMap = buildForecastData(forecastObj);
    }

    public String getValue(String key) {
        return dataMap.getValue(key) + "";
    }

    public ForecastData get() {
        return dataMap;
    }

    public ForecastData buildForecastData(JSONObject forecastCurrentlyJsonObject) {
        return new ForecastData(forecastCurrentlyJsonObject);
    }

}

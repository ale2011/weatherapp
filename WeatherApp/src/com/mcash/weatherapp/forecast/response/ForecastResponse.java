/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response;

import com.mcash.weatherapp.forecast.response.types.Alerts;
import com.mcash.weatherapp.forecast.response.types.Currently;
import com.mcash.weatherapp.forecast.response.types.Daily;
import com.mcash.weatherapp.forecast.response.types.Flags;
import com.mcash.weatherapp.forecast.response.types.Hourly;
import com.mcash.weatherapp.forecast.response.types.Minutely;
import com.mcash.weatherapp.forecast.response.types.ForecastData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class ForecastResponse {

    private Currently mOutputCurrently;
    private Minutely mOutputMinutely;
    private Hourly mOutputHourly;
    private Daily mOutputDaily;
    private Alerts mOutputAlerts;
    private Flags mOutputFlags;

    private String latitude;
    private String longitude;
    private String timezone;
    private String offset;

    private String RESPONSE;

    public ForecastResponse(String response) {
        RESPONSE = response;
        JSONObject forecastObj = null;

        try {
            forecastObj = new JSONObject(response);
            
            latitude = forecastObj.getDouble("latitude") + "";
            longitude = forecastObj.getDouble("longitude") + "";
            timezone = forecastObj.getString("timezone");
            offset = forecastObj.getDouble("offset") + "";

            if (forecastObj.has("currently")) {
                JSONObject currentlyJSONObject = forecastObj.getJSONObject("currently");
                mOutputCurrently = buildForecastCurrently(currentlyJSONObject);
            }
            if (forecastObj.has("daily")) {
                JSONObject dailyJSONObject = forecastObj.getJSONObject("daily");
                mOutputDaily = buildForecastDaily(dailyJSONObject);
            }
            if (forecastObj.has("hourly")) {
                JSONObject hourlyJSONObject = forecastObj.getJSONObject("hourly");
                mOutputHourly = buildForecastHourly(hourlyJSONObject);
            }
            if (forecastObj.has("minutely")) {
                JSONObject minutelyJSONObject = forecastObj.getJSONObject("minutely");
                mOutputMinutely = buildForecastMinutely(minutelyJSONObject);
            }
            if (forecastObj.has("alerts")) {
                JSONArray alertsJSONArray = forecastObj.getJSONArray("alerts");
                mOutputAlerts = buildForecastAlerts(alertsJSONArray);
            }
            if (forecastObj.has("flags")) {
                JSONObject flagsJSONObject = forecastObj.getJSONObject("flags");
                mOutputFlags = buildForecastFlags(flagsJSONObject);
            }
        } 
        catch (JSONException e) {
            return;
        }
    }
    
    public ForecastData[] getDataPoints(String keyString) {
        ForecastData[] value = null;
        try {
            if (keyString == "minutely") {
                value = getMinutely().getData();
            } else if (keyString == "hourly") {
                value = getHourly().getData();
            } else if (keyString == "daily") {
                value = getDaily().getData();
            }
        } catch (NullPointerException e) {
            return null;
        }
        return value;
    }
    
    public Currently getCurrently() {
	return mOutputCurrently;
    }
	
    public Currently buildForecastCurrently(JSONObject forecastJsonObject) {
        return new Currently(forecastJsonObject);
    }

    public Minutely getMinutely() {
        return mOutputMinutely;
    }

    public Minutely buildForecastMinutely(JSONObject forecastJsonObject) {
        return new Minutely(forecastJsonObject);
    }

    public Hourly getHourly() {
        return mOutputHourly;
    }

    public Hourly buildForecastHourly(JSONObject forecastJsonObject) {
        return new Hourly(forecastJsonObject);
    }

    public Daily getDaily() {
        return mOutputDaily;
    }

    public Daily buildForecastDaily(JSONObject forecastJsonObject) {
        return new Daily(forecastJsonObject);
    }

    public Alerts getAlerts() {
        return mOutputAlerts;
    }

    public Alerts buildForecastAlerts(JSONArray forecastJsonArray) {
        return new Alerts(forecastJsonArray);
    }

    public Flags getFlags() {
        return mOutputFlags;
    }

    public Flags buildForecastFlags(JSONObject forecastJsonObject) {
        return new Flags(forecastJsonObject);
    }
}

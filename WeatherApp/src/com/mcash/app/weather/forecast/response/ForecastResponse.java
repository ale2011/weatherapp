/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.forecast.response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mcash.app.weather.forecast.response.types.Alerts;
import com.mcash.app.weather.forecast.response.types.Currently;
import com.mcash.app.weather.forecast.response.types.Daily;
import com.mcash.app.weather.forecast.response.types.Flags;
import com.mcash.app.weather.forecast.response.types.ForecastData;
import com.mcash.app.weather.forecast.response.types.Hourly;
import com.mcash.app.weather.forecast.response.types.Minutely;

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
                mOutputCurrently = buildCurrently(currentlyJSONObject);
            }
            if (forecastObj.has("daily")) {
                JSONObject dailyJSONObject = forecastObj.getJSONObject("daily");
                mOutputDaily = buildDaily(dailyJSONObject);
            }
            if (forecastObj.has("hourly")) {
                JSONObject hourlyJSONObject = forecastObj.getJSONObject("hourly");
                mOutputHourly = buildtHourly(hourlyJSONObject);
            }
            if (forecastObj.has("minutely")) {
                JSONObject minutelyJSONObject = forecastObj.getJSONObject("minutely");
                mOutputMinutely = buildMinutely(minutelyJSONObject);
            }
            if (forecastObj.has("alerts")) {
                JSONArray alertsJSONArray = forecastObj.getJSONArray("alerts");
                mOutputAlerts = buildAlerts(alertsJSONArray);
            }
            if (forecastObj.has("flags")) {
                JSONObject flagsJSONObject = forecastObj.getJSONObject("flags");
                mOutputFlags = buildFlags(flagsJSONObject);
            }
        } 
        catch (JSONException e) {
            return;
        }
    }
    
    public Currently getCurrently() {
	return mOutputCurrently;
    }
	
    public Currently buildCurrently(JSONObject forecastJsonObject) {
        return new Currently(forecastJsonObject);
    }

    public Minutely getMinutely() {
        return mOutputMinutely;
    }

    public Minutely buildMinutely(JSONObject forecastJsonObject) {
        return new Minutely(forecastJsonObject);
    }

    public Hourly getHourly() {
        return mOutputHourly;
    }

    public Hourly buildtHourly(JSONObject forecastJsonObject) {
        return new Hourly(forecastJsonObject);
    }

    public Daily getDaily() {
        return mOutputDaily;
    }

    public Daily buildDaily(JSONObject forecastJsonObject) {
        return new Daily(forecastJsonObject);
    }

    public Alerts getAlerts() {
        return mOutputAlerts;
    }

    public Alerts buildAlerts(JSONArray forecastJsonArray) {
        return new Alerts(forecastJsonArray);
    }

    public Flags getFlags() {
        return mOutputFlags;
    }

    public Flags buildFlags(JSONObject forecastJsonObject) {
        return new Flags(forecastJsonObject);
    }
}

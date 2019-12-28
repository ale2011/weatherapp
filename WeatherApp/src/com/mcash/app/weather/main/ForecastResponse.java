/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mcash.app.weather.model.Alerts;
import com.mcash.app.weather.model.Currently;
import com.mcash.app.weather.model.Daily;
import com.mcash.app.weather.model.Flags;
import com.mcash.app.weather.model.ForecastData;
import com.mcash.app.weather.model.Hourly;
import com.mcash.app.weather.model.Minutely;

/**
 *
 * @author u
 */
public class ForecastResponse {

    private Currently forecast_currently;
    private Minutely forecast_minutely;
    private Hourly forecast_hourly;
    private Daily forecast_daily;
    private Alerts forecast_alert;
    private Flags forecast_alerts;

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
                forecast_currently = buildCurrently(currentlyJSONObject);
            }
            if (forecastObj.has("daily")) {
                JSONObject dailyJSONObject = forecastObj.getJSONObject("daily");
                forecast_daily = buildDaily(dailyJSONObject);
            }
            if (forecastObj.has("hourly")) {
                JSONObject hourlyJSONObject = forecastObj.getJSONObject("hourly");
                forecast_hourly = buildtHourly(hourlyJSONObject);
            }
            if (forecastObj.has("minutely")) {
                JSONObject minutelyJSONObject = forecastObj.getJSONObject("minutely");
                forecast_minutely = buildMinutely(minutelyJSONObject);
            }
            if (forecastObj.has("alerts")) {
                JSONArray alertsJSONArray = forecastObj.getJSONArray("alerts");
                forecast_alert = buildAlerts(alertsJSONArray);
            }
            if (forecastObj.has("flags")) {
                JSONObject flagsJSONObject = forecastObj.getJSONObject("flags");
                forecast_alerts = buildFlags(flagsJSONObject);
            }
        } 
        catch (JSONException e) {
            return;
        }
    }
    
    public Currently getCurrently() {
	return forecast_currently;
    }
	
    public Currently buildCurrently(JSONObject forecastJsonObject) {
        return new Currently(forecastJsonObject);
    }

    public Minutely getMinutely() {
        return forecast_minutely;
    }

    public Minutely buildMinutely(JSONObject forecastJsonObject) {
        return new Minutely(forecastJsonObject);
    }

    public Hourly getHourly() {
        return forecast_hourly;
    }

    public Hourly buildtHourly(JSONObject forecastJsonObject) {
        return new Hourly(forecastJsonObject);
    }

    public Daily getDaily() {
        return forecast_daily;
    }

    public Daily buildDaily(JSONObject forecastJsonObject) {
        return new Daily(forecastJsonObject);
    }

    public Alerts getAlerts() {
        return forecast_alert;
    }

    public Alerts buildAlerts(JSONArray forecastJsonArray) {
        return new Alerts(forecastJsonArray);
    }

    public Flags getFlags() {
        return forecast_alerts;
    }

    public Flags buildFlags(JSONObject forecastJsonObject) {
        return new Flags(forecastJsonObject);
    }
}

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
        } catch (JSONException e) {
            return;
        }

        try {
            latitude = forecastObj.getString("latitude");
            longitude = forecastObj.getString("longitude");
            timezone = forecastObj.getString("timezone");
            offset = forecastObj.getString("offset");

            if (forecastObj.has("currently")) {
                JSONObject currentlyJSONObject = forecastObj.getJSONObject("currently");
                mOutputCurrently = buildForecastIOCurrently(currentlyJSONObject);
            }
            if (forecastObj.has("daily")) {
                JSONObject dailyJSONObject = forecastObj.getJSONObject("daily");
                mOutputDaily = buildForecastIODaily(dailyJSONObject);
            }
            if (forecastObj.has("hourly")) {
                JSONObject hourlyJSONObject = forecastObj.getJSONObject("hourly");
                mOutputHourly = buildForecastIOHourly(hourlyJSONObject);
            }
            if (forecastObj.has("minutely")) {
                JSONObject minutelyJSONObject = forecastObj.getJSONObject("minutely");
                mOutputMinutely = buildForecastIOMinutely(minutelyJSONObject);
            }
            if (forecastObj.has("alerts")) {
                JSONArray alertsJSONArray = forecastObj.getJSONArray("alerts");
                mOutputAlerts = buildForecastIOAlerts(alertsJSONArray);
            }
            if (forecastObj.has("flags")) {
                JSONObject flagsJSONObject = forecastObj.getJSONObject("flags");
                mOutputFlags = buildForecastIOFlags(flagsJSONObject);
            }
        } catch (JSONException e) {
        }
    }
    
    public String getValue(String keyString) {
        String[] fields = keyString.split("-");
        String level = fields[0];
        String value = null;

        try {
            if (level.equals(new String("latitude"))) {
                value = latitude;
            } else if (level.equals(new String("longitude"))) {
                value = longitude;
            } else if (level.equals(new String("timezone"))) {
                value = timezone;
            } else if (level.equals(new String("offset"))) {
                value = offset;
            } else if (level.equals(new String("currently"))) {
                value = getCurrently().getValue(fields[1]);
            } else if (level.equals(new String("minutely"))) {
                try {
                    int listIndex = Integer.parseInt(fields[1]);
                    value = getMinutely().getData()[listIndex].getValue(fields[2]);
                } catch (NumberFormatException e) {
                    value = getMinutely().getValue(fields[1]);
                }
            } else if (level.equals(new String("hourly"))) {
                try {
                    int listIndex = Integer.parseInt(fields[1]);
                    value = getHourly().getData()[listIndex].getValue(fields[2]);
                } catch (NumberFormatException e) {
                    value = getHourly().getValue(fields[1]);
                }
            } else if (level.equals(new String("daily"))) {
                try {
                    int listIndex = Integer.parseInt(fields[1]);
                    value = getDaily().getData()[listIndex].getValue(fields[2]);
                } catch (NumberFormatException e) {
                    value = getDaily().getValue(fields[1]);
                }
            } else if (level.equals(new String("alerts"))) {
                try {
                    int listIndex = Integer.parseInt(fields[1]);
                    value = getAlerts().getData()[listIndex].getValue(fields[2]);
                } catch (NumberFormatException e) {
                    value = getAlerts().getData()[0].getValue(fields[1]);
                }
            } else if (level.equals(new String("flags"))) {
                value = getFlags().getValue(fields[1]);
            }
        } catch (NullPointerException e) {
            return null;
        }
        return value;
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
	
	public Currently buildForecastIOCurrently(JSONObject forecastJsonObject) {
		return new Currently(forecastJsonObject);
	}

	public Minutely getMinutely() {
		return mOutputMinutely;
	}
	
	public Minutely buildForecastIOMinutely(JSONObject forecastJsonObject) {
		return new Minutely(forecastJsonObject);
	}

	public Hourly getHourly() {
		return mOutputHourly;
	}

	public Hourly buildForecastIOHourly(JSONObject forecastJsonObject) {
		return new Hourly(forecastJsonObject);
	}

	public Daily getDaily() {
		return mOutputDaily;
	}
	
	public Daily buildForecastIODaily(JSONObject forecastJsonObject) {
		return new Daily(forecastJsonObject);
	}

	public Alerts getAlerts() {
		return mOutputAlerts;
	}
	
	public Alerts buildForecastIOAlerts(JSONArray forecastJsonArray) {
		return new Alerts(forecastJsonArray);
	}
	
	public Flags getFlags() {
		return mOutputFlags;
	}
	
	public Flags buildForecastIOFlags(JSONObject forecastJsonObject) {
		return new Flags(forecastJsonObject);
	}
}

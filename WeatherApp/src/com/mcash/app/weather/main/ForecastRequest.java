/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u
 */
public class ForecastRequest {
    
    private final String BASE_URL = "https://api.forecast.io/forecast/";
    private String FORECAST_URL = "";
    private String FORECAST_RESPONSE_STRING = "";
    private String USER_PARAMS = "";
    private String API = "";
    private Double LAT = 0.0;
    private Double LON = 0.0;
    
    private HashMap <String, String> OPTIONS;
    private ForecastResponse FORECAST_RESPONSE = null;
    
    public ForecastRequest(String api, Double lat, Double lon)
    {
        this.API = api;
        this.LAT = lat;
        this.LON = lon;
    }
    
    public void setRequestParams(HashMap <String, String> options)
    {
        this.OPTIONS = options;
    }
    
    public URL generateForecastURL()
    {
        URL url = null;
        String forecastUrlString = BASE_URL + API + "/" + LAT + "," + LON + "?";

        try {
            //TODO add in the time parameter for time machine requests
            for(String key: OPTIONS.keySet())
            {
                if(key == "userAgent")
                    USER_PARAMS = OPTIONS.get(key);
                else
                    forecastUrlString += "&" + key + "=" + OPTIONS.get(key);                
            }
            url = new URL(forecastUrlString);
            FORECAST_URL = forecastUrlString;  
        } catch (MalformedURLException ex) {
            Logger.getLogger(ForecastRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
    }
    
    //////////////////////////////////////////////////////////////
    
    public String sendRequest()
    {
        // build send request
        URL url = generateForecastURL();
        
        // send request & receive response
        //this.FORECAST_RESPONSE = makeForecastRequest(url);
        
        HttpURLConnection conn = null;
        String responseString = "";
        
        try 
        {
            conn = (HttpURLConnection) url.openConnection();
            if(USER_PARAMS != null) 
            {
            	conn.setRequestProperty("User-Agent", USER_PARAMS);
            }
            conn.setDoOutput(false);
            BufferedReader reader = null;
            
            try 
            {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) 
                {
                	responseString = line;
                }
            } 
            catch (IOException e) {
            	e.printStackTrace();
            } 
            finally 
            {
                if (reader != null) 
                {
                	reader.close();
                    reader = null;                    
                }
            }
        } 
        catch (IOException e) {
        	e.printStackTrace();
        }
        
        FORECAST_RESPONSE_STRING = responseString;
        FORECAST_RESPONSE = new ForecastResponse(FORECAST_RESPONSE_STRING);
        return this.FORECAST_RESPONSE_STRING;
    }        
    
    public String getURL()
    {
        return this.FORECAST_URL;
    }
    
    public String getResponseString()
    {
        return this.FORECAST_RESPONSE_STRING;
    }
    
    public ForecastResponse getForecastResponse()
    {
        return this.FORECAST_RESPONSE;
    }
    
}

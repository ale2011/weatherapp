/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.forecast.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mcash.app.weather.forecast.response.ForecastResponse;

/**
 *
 * @author u
 */
public class ForecastRequest {
    
    private final String BASE_URL = "https://api.forecast.io/forecast/";
    private String FORECAST_URL;
    private String FORECAST_RESPONSE;
    private String USER_PARAMS;
    private String API;
    private Double LAT;
    private Double LON;
    
    private HashMap <String, String> OPTIONS;
    
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
    
    public void sendRequest()
    {
        // build send request
        URL url = generateForecastURL();
        // send request & receive response
        this.FORECAST_RESPONSE = makeForecastRequest(url);
    }    
    
    /**
     * do actual request
     * @param url
     * @return 
     */
    public String makeForecastRequest(URL url)
    {
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
            } 
            finally 
            {
                if (reader != null) 
                {
                    try 
                    {
                        reader.close();
                        reader = null;
                    } 
                    catch (IOException e) {
                    }
                }
            }
        } 
        catch (IOException e1) {
        }
        return responseString;
    }
    
    public String getURL()
    {
        return this.FORECAST_URL;
    }
    
    public String getResponseString()
    {
        return this.FORECAST_RESPONSE;
    }
    
    public ForecastResponse getForecastResponse()
    {
        return generateForecastResponse(FORECAST_RESPONSE);
    }
    
    /**
     * Build the ForecastResponse object from the response
     * @param response the response string from the request
     * @return ForecastResponse object
     * @see ForecastResponse
     */
    public ForecastResponse generateForecastResponse(String response)
    {
        return new ForecastResponse(response);
    }
}

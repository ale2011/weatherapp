/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.main;

import com.mcash.weatherapp.forecast.request.ForecastRequest;
import com.mcash.weatherapp.forecast.response.ForecastResponse;
import com.mcash.weatherapp.forecast.response.types.Alerts;
import com.mcash.weatherapp.forecast.response.types.Currently;
import com.mcash.weatherapp.forecast.response.types.Daily;
import com.mcash.weatherapp.forecast.response.types.Flags;
import com.mcash.weatherapp.forecast.response.types.ForecastData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 *
 * @author u
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException 
    {
        ForecastRequest request = new ForecastRequest("060d1fe78becf9f42a22c5267fff8697",39.371778,-74.624684);
        HashMap<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("units", "us");
        requestParam.put("userAgent", "Custom User Agent 1.0");
        request.setRequestParams(requestParam);
        request.sendRequest();
        
        String responseString = request.getResponseString();  
        ForecastResponse response = new ForecastResponse(responseString);  
   
        
        Currently c = response.getCurrently();
        //c.toDisplay();
        
        Daily d = response.getDaily();
        d.toDisplay();
        
        
        
        
    }
    
}

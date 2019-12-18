/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.main;

import com.mcash.weatherapp.forecast.request.ForecastRequest;
import com.mcash.weatherapp.forecast.response.ForecastResponse;
import com.mcash.weatherapp.forecast.response.types.ForecastData;
import java.util.HashMap;

/**
 *
 * @author u
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ForecastRequest request = new ForecastRequest("060d1fe78becf9f42a22c5267fff8697",42.3601,-71.0589);
        HashMap<String, String> requestParam = new HashMap<String, String>();
	requestParam.put("units", "us");
	requestParam.put("userAgent", "Custom User Agent 1.0");
        request.setRequestParams(requestParam);
        request.sendRequest();
        
        String responseString = request.getResponseString();  
        ForecastResponse response = new ForecastResponse(responseString);  

        ForecastData[] hourlyPoints = response.getDataPoints("hourly");
        ForecastData[] dailyPoints = response.getDataPoints("daily");
        
                
        System.out.println(request.getResponseString());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.app.weather.main;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException 
    {
    	ForecastRequest request = new ForecastRequest("060d1fe78becf9f42a22c5267fff8697",39.371778,-74.624684);
        HashMap<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("units", "us");
        request.setRequestParams(requestParam);
        request.sendRequest();
        
        String responseString = request.getResponseString();  
        ForecastResponse response = new ForecastResponse(responseString);  
        
        Currently c = response.getCurrently();
        c.toDisplay();
        
        Daily d = response.getDaily();
        //d.toDisplay();
        
        Hourly h = response.getHourly();
        h.toDisplay();
        
        Minutely m = response.getMinutely();
        //m.toDisplay();
    }
    
}
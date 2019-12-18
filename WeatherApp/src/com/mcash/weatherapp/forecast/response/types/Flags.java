/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcash.weatherapp.forecast.response.types;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author u
 */
public class Flags {

    private HashMap<String, String> dataMap;

    public Flags(JSONObject jsonObject) {
        dataMap = new HashMap<String, String>();
        try {
            String darkskyunavailable = jsonObject.getString("darksky-unavailable");
            dataMap.put("darkskyunavailable", darkskyunavailable);
        } catch (JSONException e) {
        }

        try {
            String metnolicense = jsonObject.getString("metno-license");
            dataMap.put("metnolicense", metnolicense);
        } catch (JSONException e) {
        }

        try {
            String units = jsonObject.getString("units");
            dataMap.put("units", units);
        } catch (JSONException e) {
        }
    }
    public String getValue(String key) {
        return dataMap.get(key) + "";
    }
}

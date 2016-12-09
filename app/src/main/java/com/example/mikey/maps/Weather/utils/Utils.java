package com.example.mikey.maps.Weather.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Workstation2.0 on 11/19/2016.
 */

public class Utils {

    public static final String Base_URL = "http://api.openweathermap.org/data/2.5/weather?";
    public static final String Icon_URL = "http://api.openweathermap.org/img/w/";
    public static final String APP_ID = "c4efddcd88ba8f02bee817ba90c8e05b";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";

    public static String apiRequest(String lat, String lon){
        StringBuilder sb = new StringBuilder(Base_URL);
        sb.append(String.format("lat=%s&lon=%s&APPID=%s&units=imperial",lat,lon,APP_ID));
        return sb.toString();
    }

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException{
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return jObj;
    }
    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }
    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return(float) jsonObject.getDouble(tagName);
    }
    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException{
        return(float) jsonObject.getDouble(tagName);
    }
    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }
}


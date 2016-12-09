package com.example.mikey.maps.Weather.data;

import com.example.mikey.maps.MapsActivity;
import com.example.mikey.maps.Weather.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Workstation2.0 on 11/19/2016.
 */

public class WeatherHTTPClient {


    public String getWeatherData(String lat, String lon){
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            //Query
            connection = (HttpURLConnection)(new URL(Utils.apiRequest(lat,lon))).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            //Read Query Response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line +"\r\n");
            }
            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    }


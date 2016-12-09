package com.example.mikey.maps.Weather.data;



import com.example.mikey.maps.Weather.Model.Place;
import com.example.mikey.maps.Weather.Model.Weather;
import com.example.mikey.maps.Weather.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Workstation2.0 on 11/19/2016.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data){
      Weather weather = new Weather();
        //create JSON Object from data
        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getDouble("lat", coordObj));
            place.setLon(Utils.getDouble("lon", coordObj));

            //Get sys object from API
            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country",sysObj));
            place.setLastupdate(Utils.getInt("dt", jsonObject));
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset",sysObj));
            place.setCity(Utils.getString("name", jsonObject));
            weather.local = place;

            //Get Weather Info
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.cc.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.cc.setDescription(Utils.getString("description", jsonWeather));
            weather.cc.setCondition(Utils.getString("main", jsonWeather));
            weather.cc.setIcon(Utils.getString("icon", jsonWeather));
            JSONObject mainObj = Utils.getObject("main",jsonObject);
            weather.cc.setHumidity(Utils.getInt("humidity", mainObj));
            weather.cc.setTemperature(Utils.getFloat("temp",mainObj));
            weather.cc.setMaxTemp(Utils.getFloat("temp_max", mainObj));
            weather.cc.setMinTemp(Utils.getFloat("temp_min",mainObj));
            weather.cc.setPressure(Utils.getInt("pressure",mainObj));

            //Get Wind
            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed",windObj));
            weather.wind.setDegree(Utils.getFloat("deg",windObj));

            //Get Clouds
            JSONObject cloudObj = Utils.getObject("clouds",jsonObject);
            weather.cloud.setPrecipitation(Utils.getInt("all",cloudObj));

            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

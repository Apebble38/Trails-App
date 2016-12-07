package com.example.mikey.maps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikey.maps.Weather.Model.Weather;
import com.example.mikey.maps.Weather.data.JSONWeatherParser;
import com.example.mikey.maps.Weather.data.WeatherHTTPClient;
import com.example.mikey.maps.Weather.utils.Utils;

import org.apache.http.client.HttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class weatherUI extends AppCompatActivity {
    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;

    Weather weather = new Weather();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_ui);

        cityName = (TextView) findViewById(R.id.cityText);
        iconView = (ImageView) findViewById(R.id.weatherIcon);
        temp = (TextView) findViewById(R.id.tempText);
        description = (TextView) findViewById(R.id.cloudText);
        humidity = (TextView) findViewById(R.id.humidText);
        pressure = (TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);
        updated = (TextView) findViewById(R.id.updateText);

        renderWeatherData("Oswego,US");


    }
    public void renderWeatherData(String city){
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(city +"&units=imperial");

    }
    private class WeatherTask extends AsyncTask<String,Void,Weather>{



        @Override
        protected Weather doInBackground(String... params) {
           String data = ((new WeatherHTTPClient().getWeatherData(params[0])));

            weather = JSONWeatherParser.getWeather(data);

            Log.v("Data:",weather.local.getCity());
            return weather;
        }
        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            long unixUpdated = weather.local.getLastupdate() * 1000;

            java.util.Date updatedDate = new java.util.Date(unixUpdated);
            String updatedTime = String.valueOf(android.text.format.DateFormat.format("kk:mm:ss zzz", updatedDate));

            DecimalFormat decimalFormat = new DecimalFormat("#.#");

            String tempFormat = decimalFormat.format(weather.cc.getTemperature());

            cityName.setText(weather.local.getCity()+"," + weather.local.getCountry());
            temp.setText("" + tempFormat+ "F");
            humidity.setText("Humidity: " + weather.cc.getHumidity()+"%");
            pressure.setText("Pressure: " + weather.cc.getPressure() + "mb");
            wind.setText("Wind: " + weather.wind.getSpeed() +" mph");
            updated.setText("Last Updated: " + updatedTime);
            description.setText("Condition: " + weather.cc.getCondition() +"(" + weather.cc.getDescription() + ")");

            new DownloadImage().execute(Utils.ICON_URL + weather.cc.getIcon() +".png");

        }
    }
    private class ImageDownload extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }
    //--- DownloadImage START ---//

    public class DownloadImage extends AsyncTask<String, Integer, Drawable>{

        @Override
        protected Drawable doInBackground(String... arg0){
            // This is done in a background thread
            return downloadImage(arg0[0]);
        }

        /**
         * Called after the image has been downloaded
         * -> this calls a function on the main thread again
         */
        protected void onPostExecute(Drawable image)
        {
            //x.setImageDrawable(image);
            //Sets the Icon as the image that's been downloaded
            iconView.setImageDrawable(image);
        }

        /**
         * Actually download the Image from the _url
         * @param _url
         * @return
         */
        private Drawable downloadImage(String _url)
        {
            //Prepare to download image
            URL url;
            BufferedOutputStream out;
            InputStream in;
            BufferedInputStream buf;

            //BufferedInputStream buf;
            try {
                url = new URL(_url);
                in = url.openStream();


                // Read the inputstream
                buf = new BufferedInputStream(in);

                // Convert the BufferedInputStream to a Bitmap
                Bitmap bMap = BitmapFactory.decodeStream(buf);
                if (in != null) {
                    in.close();
                }
                if (buf != null) {
                    buf.close();
                }

                return new BitmapDrawable(bMap);

            } catch (Exception e) {
                Log.e("Error reading file", e.toString());
            }
            return null;
        }
    }
    //--- DownloadImage End ---//


}

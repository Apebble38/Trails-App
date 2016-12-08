package com.example.mikey.maps;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mikey.maps.R.styleable.View;

public class FitnessActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        count = (TextView) findViewById(R.id.count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        count.setText("Ready to Start?");

    }


    public void reset(View view){
        count.setText(String.valueOf(0));
        //sensorEvent.values[0] = 0;
    }


    @Override
    public void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onPause(){
        super.onPause();
        activityRunning = false;

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(activityRunning){
            //count.setText("Are you ready?");
            count.setText(String.valueOf(sensorEvent.values[0]));
        } else {
            sensorEvent.values[0] = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

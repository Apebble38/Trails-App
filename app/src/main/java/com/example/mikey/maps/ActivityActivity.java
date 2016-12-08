package com.example.mikey.maps;

import android.app.Activity;
import android.graphics.Color;
import android.icu.util.TimeUnit;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikey.maps.Trails.Trail;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityActivity extends Activity {
    TextView trailName;
    TextView timerField;
    Button startStop;
    Button reset;
    Button save;
    boolean counting;
    Chronometer timer;
    long startTime;
    long countUp;
    long elapsedTimeBeforePause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        Bundle b = getIntent().getExtras();
        final Trail trail = b.getParcelable("com.package.Trail");
        trailName = (TextView)findViewById(R.id.trailTitle);
        timerField = (TextView)findViewById(R.id.timerField);
        trailName.setText(trail.getName());
        startStop = (Button) findViewById(R.id.StartStop);
        reset = (Button) findViewById(R.id.reset);
        save = (Button) findViewById(R.id.save);
        timer = (Chronometer) findViewById(R.id.timer);
        elapsedTimeBeforePause = 0;
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer arg0) {
                countUp = (SystemClock.elapsedRealtime() - arg0.getBase()) / 1000;
                String asText = (countUp / 60) + ":" + (countUp % 60);
                timerField.setText(asText);
            }
        });
        counting =false;
        startStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(counting == false){
                    timer.setBase(SystemClock.elapsedRealtime() - elapsedTimeBeforePause);
                    timer.start();
                    startStop.setText("Stop");
                    startStop.setBackgroundColor(Color.RED);
                    counting = true;
                }
                else if(counting == true){

                    elapsedTimeBeforePause = SystemClock.elapsedRealtime() - timer.getBase();
                    timer.stop();
                    showElapsedTime();
                    startStop.setText("Start");
                    startStop.setBackgroundColor(Color.GREEN);
                    counting = false;
                }
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(counting == true){
                    timer.stop();
                    showElapsedTime();
                    startStop.setText("Start");
                    startStop.setBackgroundColor(Color.GREEN);
                }
                timer.setBase(0);
                timerField.setText("");
                elapsedTimeBeforePause = 0;

            }
        });


    }
    private void showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - timer.getBase();
        Toast.makeText(ActivityActivity.this, "Elapsed milliseconds: " + elapsedMillis,
                Toast.LENGTH_SHORT).show();
    }

}

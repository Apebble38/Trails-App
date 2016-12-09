package com.example.mikey.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);

                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(splashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        };

        timer.start();
    }

}


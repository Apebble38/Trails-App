package com.example.mikey.maps;

import android.content.Intent;
<<<<<<< HEAD
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mikey.maps.Facebook.FacebookLogin;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
>>>>>>> refs/remotes/origin/master

public class optionsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_list);
    }

<<<<<<< HEAD
    public void trailsClick(View view){
        Intent intent = new Intent(this,trailsActivity.class);
        startActivity(intent);
    }

    public void weatherClick(View view) {

        Intent intent = new Intent(this, weatherUI.class);
        startActivity(intent);

    }

    public void faceClick(View view) {

        Intent intent = new Intent(this, FacebookLogin.class);
        startActivity(intent);

=======
    public void onClick(View view){
        Intent intent = new Intent(this, weatherUI.class);
        startActivity(intent);
>>>>>>> refs/remotes/origin/master
    }
}

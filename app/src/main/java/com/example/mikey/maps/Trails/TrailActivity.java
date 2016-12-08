package com.example.mikey.maps.Trails;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mikey.maps.ActivityActivity;
import com.example.mikey.maps.MapsActivity;
import com.example.mikey.maps.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class TrailActivity extends Activity {
    TextView trailName;
    TextView trailType;
    TextView trailDescription;
    TextView txtLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail);
        Bundle b = getIntent().getExtras();
        final Trail trail = b.getParcelable("com.package.Trail");
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());


        trailName = (TextView)findViewById(R.id.trailName);
        trailType = (TextView)findViewById(R.id.trailType);
        trailDescription = (TextView) findViewById(R.id.trailDescription);

        String trailActivities = Arrays.toString(trail.getType());
        trailActivities = trailActivities.replace("[","");
        trailActivities = trailActivities.replace("]","");
        trailName.setText(trail.getName());
        trailType.setText(trailActivities);
        trailDescription.setText(trail.getDescription());


        Button button = (Button) findViewById(R.id.SeeOnMap);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(TrailActivity.this,MapsActivity.class);
                intent.putExtra("com.package.Trail",trail);
                startActivity(intent);
            }
        });

        Button ActivityButton = (Button) findViewById(R.id.Start_Activity);
        ActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(TrailActivity.this,ActivityActivity.class);
                intent.putExtra("com.package.Trail",trail);
                startActivity(intent);
            }
        });
    }


}

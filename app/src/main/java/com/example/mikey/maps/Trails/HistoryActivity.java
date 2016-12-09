package com.example.mikey.maps.Trails;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.mikey.maps.R;

public class HistoryActivity extends Activity {

    private TrailHistory trailHistory;
    private TextView trailTitle;
    private TextView date;
    private TextView duration;
    private TextView steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Bundle b = getIntent().getExtras();
        trailHistory = b.getParcelable("com.package.TrailHistory");

        trailTitle = (TextView)findViewById(R.id.trailTitle);
        date = (TextView) findViewById(R.id.date);
        duration = (TextView) findViewById(R.id.duration);
        steps = (TextView) findViewById(R.id.numberOfSteps);

        trailTitle.setText(trailHistory.getTrailName());
        date.setText(trailHistory.getDate());
        long lDuration = trailHistory.getDuration();

        String sDuration = lDuration+"";
        duration.setText("Duration" + sDuration);

        steps.setText("Steps" + trailHistory.getSteps()+"");
    }

}

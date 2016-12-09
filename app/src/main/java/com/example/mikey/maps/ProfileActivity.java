package com.example.mikey.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mikey.maps.Trails.HistoryActivity;
import com.example.mikey.maps.Trails.HistoryAdapter;
import com.example.mikey.maps.Trails.Trail;
import com.example.mikey.maps.Trails.TrailActivity;
import com.example.mikey.maps.Trails.TrailHistory;
import com.example.mikey.maps.Trails.TrailHistoryDatabaseOps;
import com.example.mikey.maps.Trails.trailsActivity;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private HistoryAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        listView = (ListView) findViewById(R.id.list);
        TrailHistoryDatabaseOps data = new TrailHistoryDatabaseOps(this);
        ArrayList<TrailHistory> historyList =(ArrayList<TrailHistory>) data.getAllHistories();
        if(historyList==null){
            ArrayList<String> emptyArray = new ArrayList<String>();
            emptyArray.add("No History");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, emptyArray);
            listView.setAdapter(adapter);
        }
        else {
            adapter = new HistoryAdapter(this, historyList);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TrailHistory trailHistory = (TrailHistory) listView.getItemAtPosition(position);
                Intent intent = new Intent(ProfileActivity.this,HistoryActivity.class);
                intent.putExtra("com.package.TrailHistory",trailHistory);
                startActivity(intent);

            }
        });
    }
}

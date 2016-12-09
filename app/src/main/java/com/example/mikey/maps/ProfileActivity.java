package com.example.mikey.maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mikey.maps.Trails.HistoryAdapter;
import com.example.mikey.maps.Trails.Trail;
import com.example.mikey.maps.Trails.TrailHistory;
import com.example.mikey.maps.Trails.TrailHistoryDatabaseOps;

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
    }
}

package com.example.mikey.maps.Trails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mikey.maps.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Workstation2.0 on 12/8/2016.
 */

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<TrailHistory> historyList;

    public HistoryAdapter(Context context,List<TrailHistory> values){
        this.context = context;
        this.historyList = values;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public int getCount(){
        //System.out.println("trailsList size: " + trailList.size());
        return historyList.size();
    }
    public TrailHistory getItem(int position){
        return historyList.get(position);
    }
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent){
        View updateView;
        HistoryAdapter.ViewHolder holder;
        if(view == null){
            updateView = mLayoutInflater.inflate(R.layout.history_listitem,null);
            holder = new ViewHolder();

            holder.name = (TextView) updateView.findViewById(R.id.trailName);
            holder.date = (TextView) updateView.findViewById(R.id.date);

            updateView.setTag(holder);
        }else{
            updateView = view;
            holder = (HistoryAdapter.ViewHolder) updateView.getTag();
        }
        final TrailHistory item = getItem(position);

        holder.name.setText(item.getTrailName());
        holder.date.setText(item.getDate());

        return updateView;


    }


    static class ViewHolder{
        TextView name;
        TextView date;
    }




}

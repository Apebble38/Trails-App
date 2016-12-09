package com.example.mikey.maps.Trails;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.mikey.maps.R;
import com.example.mikey.maps.Trails.Trail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Trooper on 12/4/2016.
 */

public class TrailAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Trail> trailList;
    private List<Trail> untouchedTrails;
    private List<Trail> filteredList;
    private TrailFilter trailFilter;


    public TrailAdapter(Context context,List<Trail> values){
        this.context = context;
        this.trailList = values;
        this.filteredList = values;
        this.untouchedTrails = values;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public int getCount(){
        //System.out.println("trailsList size: " + trailList.size());
        return trailList.size();
    }
    public Trail getItem(int position){
        return trailList.get(position);
    }
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        View updateView;
        ViewHolder holder;
        if(view == null){
            updateView = mLayoutInflater.inflate(R.layout.trail_listitem,null);
            holder = new ViewHolder();

            holder.name = (TextView) updateView.findViewById(R.id.name);
            holder.activity = (TextView) updateView.findViewById(R.id.activity);

            updateView.setTag(holder);
        }else{
            updateView = view;
            holder = (ViewHolder) updateView.getTag();
        }
        final Trail item = getItem(position);
            String activities = Arrays.toString(item.getType());
            activities = activities.replace("[","");
            activities = activities.replace("]","");
            holder.name.setText(item.getName());
            holder.activity.setText("Activities: " + activities);

        return updateView;


    }


    @Override
    public Filter getFilter() {
        if (trailFilter == null) {
            trailFilter = new TrailFilter();
        }
        return trailFilter;
    }

    static class ViewHolder{
        TextView name;
        TextView activity;
    }


    private class TrailFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
        //System.out.println("constraint: " + constraint);
        //below checks the match for the cityId and adds to the filterlist
            //long cityId= Long.parseLong(constraint.toString());
            FilterResults results = new FilterResults();

            if (constraint.equals("A-Z")) {
                trailList = untouchedTrails;
                filteredList = trailList;
                //System.out.println("trails list length " +trailList.size());
                //System.out.println("filteredList length " + filteredList.size());
                Collections.sort(filteredList, new Comparator<Trail>() {
                    public int compare(Trail result1, Trail result2) {
                        return result1.getName().compareTo(result2.getName());
                    }
                });
                //System.out.println("filteredList size " +filteredList.size());
                results.count = filteredList.size();
                results.values = filteredList;

            }
            else if(constraint.equals("Z-A")){
                trailList = untouchedTrails;
                filteredList=trailList;
                Collections.sort(filteredList, new Comparator<Trail>() {
                    public int compare(Trail result1, Trail result2) {
                        return result2.getName().compareTo(result1.getName());
                    }
                });
                //System.out.println("filteredList size " +filteredList.size());
                results.count = filteredList.size();
                results.values = filteredList;


            }
            else if(constraint.equals("Hiking")){
                trailList = untouchedTrails;
                filteredList = new ArrayList<Trail>();
                for(Trail x: trailList){
                    String[] activities = x.getType();
                    //System.out.println("activities " + activities[0]);
                    for(int i = 0; i < activities.length;i++){
                        if(activities[i].equals("hiking")){
                            //System.out.println("adding");
                            filteredList.add(x);
                        }
                    }
                }

                Collections.sort(filteredList, new Comparator<Trail>() {
                    public int compare(Trail result1, Trail result2) {
                        return result1.getName().compareTo(result2.getName());
                    }
                });


                results.count = filteredList.size();
                results.values = filteredList;
            }

            else if(constraint.equals("Biking")){
                trailList = untouchedTrails;
                filteredList = new ArrayList<Trail>();
                for(Trail x: trailList){
                    String[] activities = x.getType();
                    for(int i = 0; i < activities.length;i++){
                        //System.out.println("activities " + activities[i]);
                        if(activities[i].equals("biking")){
                            filteredList.add(x);
                        }
                    }
                }

                Collections.sort(filteredList, new Comparator<Trail>() {
                    public int compare(Trail result1, Trail result2) {
                        return result1.getName().compareTo(result2.getName());
                    }
                });

                results.count = filteredList.size();
                results.values = filteredList;
            }
            else if(constraint.equals("Walking")){
                trailList = untouchedTrails;
                filteredList = new ArrayList<Trail>();
                for(Trail x: trailList){
                    String[] activities = x.getType();
                    for(int i = 0; i < activities.length;i++){
                        //System.out.println("activities " + activities[i]);
                        if(activities[i].equals("walking")){
                            filteredList.add(x);
                        }
                    }
                }

                Collections.sort(filteredList, new Comparator<Trail>() {
                    public int compare(Trail result1, Trail result2) {
                        return result1.getName().compareTo(result2.getName());
                    }
                });

                results.count = filteredList.size();
                results.values = filteredList;
            }
            else if(constraint.equals("Distance")){
                trailList = untouchedTrails;
                filteredList=trailList;
                //System.out.println("trails list length " +trailList.size());
                //System.out.println("filteredList length " + filteredList.size());
                Collections.sort(filteredList, new Comparator<Trail>() {
                    public int compare(Trail result1, Trail result2) {
                        return result1.getName().compareTo(result2.getName());
                    }
                });
                //System.out.println("filteredList size " +filteredList.size());
                results.count = filteredList.size();
                results.values = filteredList;
            }

            else{
                trailList = untouchedTrails;
                filteredList=new ArrayList<Trail>();
                for(Trail x: trailList){
                    String[] activities = x.getType();
                    if(x.getName().toLowerCase().contains(((String)constraint).toLowerCase())){
                        filteredList.add(x);
                    }
                    for(int i = 0; i < activities.length;i++){
                        //System.out.println("activities " + activities[i]);

                        if(activities[i].toLowerCase().contains(((String)constraint).toLowerCase())){
                            filteredList.add(x);
                        }
                    }

                }
                results.count = filteredList.size();
                results.values = filteredList;

            }
            return results;
        }
        //Publishes the matches found, i.e., the selected cityids
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            trailList = (ArrayList<Trail>)results.values;
            notifyDataSetChanged();
        }
    }
}

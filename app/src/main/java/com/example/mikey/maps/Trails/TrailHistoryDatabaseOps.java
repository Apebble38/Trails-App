package com.example.mikey.maps.Trails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Workstation2.0 on 12/8/2016.
 */

public class TrailHistoryDatabaseOps extends SQLiteOpenHelper{



        //All static variables
        //database version
        public static final int database_version = 1;
        //database name
        public static final String DATABASE_NAME = "Trail_Manager";

        //table name
        public static final String TABLE_NAME = "Trail_History";

        //Trails table Columns names
        public static final String TRAIL_NAME = "trail_name";
        public static final String DATE = "Date";
        public static final String DURATION = "Duration";
        public static final String STEPS = "Steps";

        //sqlite query crates columns
        public String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + TRAIL_NAME + " TEXT,"
                + DATE + " TEXT,"
                + DURATION + " REAL,"
                + STEPS + " REAL" + " );";

        //sqlite query deletes all queries
        public static final String DELETE_QUERIESS = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public TrailHistoryDatabaseOps(Context context) {
            super(context, DATABASE_NAME, null, database_version);
            Log.d("Database operations", "Database created");
        }

        public int getDatabase_version() {
            return database_version;
        }

        public void onCreate(SQLiteDatabase sdb) {
            sdb.execSQL(CREATE_QUERY);

            Log.d("Database operations", "Table created");
        }

        public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
            sdb.execSQL(DELETE_QUERIESS);
            onCreate(sdb);
        }

        public void onDowngrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
            onUpgrade(sdb, oldVersion, newVersion);
        }

        public void addTrailHistory(TrailHistory trailHistory) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            //System.out.println("storing " + trail.getLatitude());
            values.put(TRAIL_NAME, trailHistory.getTrailName());
            values.put(DATE, trailHistory.getDate());
            values.put(DURATION, trailHistory.getDuration());
            values.put(STEPS, trailHistory.getSteps());

            //inserting row
            db.insert(TABLE_NAME, null, values);
            db.close();     //closing database connection
        }

        public TrailHistory getTrail(String name) {
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.query(TABLE_NAME, new String[]{TRAIL_NAME,
                            DATE, DURATION, STEPS},
                    TRAIL_NAME + "=?",
                    new String[]{String.valueOf(name)}, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            //System.out.println("cursor activity: " + cursor.getString(3));
            TrailHistory trailHistory = new TrailHistory(cursor.getString(0),cursor.getString(1),
                    cursor.getLong(2),cursor.getInt(3));
            return trailHistory;
        }

        public List<TrailHistory> getAllHistories() {
            List<TrailHistory> trailList = new ArrayList<TrailHistory>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    //System.out.println("cursor activity: " + cursor.getString(3));
                    //System.out.println("getting " + cursor.getString(1));

                    TrailHistory trailHistory = new TrailHistory(cursor.getString(0),cursor.getString(1),
                            cursor.getLong(2),cursor.getInt(3));

                    trailList.add(trailHistory);
                } while (cursor.moveToNext());
            }

            // return contact list
            return trailList;

        }


        public int getTrailsCount() {
            String countQuery = "SELECT  * FROM " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();

            // return count
            return count;
        }

        public int updateTrailHistory(TrailHistory trailHistory) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TRAIL_NAME, trailHistory.getTrailName());
            values.put(DATE, trailHistory.getDate());
            values.put(DURATION, trailHistory.getDuration());
            values.put(STEPS, trailHistory.getSteps());

            // updating row
            return db.update(TABLE_NAME, values, TRAIL_NAME + " = ?",
                    new String[]{String.valueOf(trailHistory.getTrailName())});
        }

        public boolean containsTrail(Trail trail) {
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.query(TABLE_NAME, new String[]{TRAIL_NAME,
                            DATE, DURATION, STEPS},
                    TRAIL_NAME + "=?",
                    new String[]{String.valueOf(trail.getName())}, null, null, null, null);
            if (cursor.moveToFirst()) {
                cursor.close();
                return true;//row was found
            } else {
                cursor.close();
                return false;//row was not found
            }
        }

        public boolean isEmpty(){
            if(getTrailsCount()==0){
                return true;
            }
            return false;
        }





}

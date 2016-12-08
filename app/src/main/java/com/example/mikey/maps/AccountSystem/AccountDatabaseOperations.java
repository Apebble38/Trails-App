package com.example.mikey.maps.AccountSystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mikey.maps.Trails.DatabaseOperations;
import com.example.mikey.maps.Trails.Trail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Trooper on 12/7/2016.
 */

public class AccountDatabaseOperations extends SQLiteOpenHelper{

    public static final int database_version = 1;
    //database name
    public static final String DATABASE_NAME = "Account_Manager";

    //table name
    public static final String TABLE_NAME = "Accounts";

    //Trails table Columns names
    public static final String USERNAME = "User_Name";
    public static final String PASSWORD = "Password";
    public static final String ACCOUNT_TYPE = "Account_Type";

    //sqlite query crates columns
    public String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            + USERNAME + " TEXT,"
            + PASSWORD + " TEXT,"
            + ACCOUNT_TYPE + " TEXT" + " );";

    //sqlite query deletes all queries
    public static final String DELETE_QUERIESS = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public AccountDatabaseOperations(Context context) {
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

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, account.getUsername());
        values.put(PASSWORD, account.getPassword());
        values.put(ACCOUNT_TYPE, account.getType());

        //inserting row
        db.insert(TABLE_NAME, null, values);
        db.close();     //closing database connection
    }

    public Account getAccount(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{USERNAME,
                        PASSWORD, ACCOUNT_TYPE},
                ACCOUNT_TYPE + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //System.out.println("cursor activity: " + cursor.getString(3));
        String activity = cursor.getString(3);

        Account account = new Account(cursor.getString(0),cursor.getString(1), cursor.getString(2));
        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<Account>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //System.out.println("cursor activity: " + cursor.getString(3));
                //System.out.println("getting " + cursor.getString(1));
                String activity = cursor.getString(3);

                Account account = new Account(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2));
                // Adding contact to list
                accountList.add(account);
            } while (cursor.moveToNext());
        }

        // return contact list
        return accountList;

    }


    public int getAccountCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERNAME, account.getUsername());
        values.put(PASSWORD, account.getPassword());
        values.put(ACCOUNT_TYPE, account.getType());

        // updating row
        return db.update(TABLE_NAME, values, ACCOUNT_TYPE + " = ?",
                new String[]{String.valueOf(account.getType())});
    }

    public boolean containsAccount(Trail trail) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{USERNAME,
                        PASSWORD, ACCOUNT_TYPE},
                ACCOUNT_TYPE + "=?",
                new String[]{String.valueOf(trail.getName())}, null, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;//row was found
        } else {
            cursor.close();
            return false;//row was not found
        }
    }

    public boolean isEmpty() {
        if (getAccountCount() == 0) {
            return true;
        }
        return false;
    }


}
package com.example.sertifikasi2.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "digitaltalent.db";

    public static final String TABLE_SQLite="sqlite";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";

    public static final String COLUMN_EMAIL = "email";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + "("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT NOT NULL," +
                COLUMN_EMAIL + " TEXT NOT NULL" +
                ")";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    //    public ArrayList<HashMap<String, String>> getAllData(){
//        ArrayList<HashMap<String, String>> wordList;
//        wordList = new ArrayList<HashMap<String, String>>();
//        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()){
//            do {
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put(COLUMN_ID, cursor.getString(0));
//                map.put(COLUMN_NAME, cursor.getString(1));
//                map.put(COLUMN_ADDRESS, cursor.getString(2));
//            }while (cursor.moveToNext());
//        }
//
//        Log.e("select sqlite ", "" +wordList);
//
//        database.close();
//        return wordList;
//    }
    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> wordList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put(COLUMN_ID, cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                map.put(COLUMN_NAME, cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                map.put(COLUMN_ADDRESS, cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                map.put(COLUMN_EMAIL, cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite", "" + wordList);

        cursor.close();
        database.close();
        return wordList;
    }

    public void insert(String name, String address, String email){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (name, address, email) " +
                " VALUES ( '" + name + "', '" + address + "', '" + email + "')";
        Log.e("insert sqlite", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

//    public void insert(String name, String address, String email) {
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("address", address);
//        values.put("email", email);
//        long result = database.insert(TABLE_SQLite, null, values);
//        Log.e("insert sqlite", "Result: " + result);
//        database.close();
//    }

    public void update(int id, String name, String address, String email){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SQLite + " SET "
                + COLUMN_NAME + "='" + name + "',"
                + COLUMN_ADDRESS + "='" + address + "',"
                + COLUMN_EMAIL + "='" + email + "'"
                + "WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";

//        String updateQuery = "DELETE FROM " + TABLE_SQLite + "WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}


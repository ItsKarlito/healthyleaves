package com.example.plantmonitor.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //declaration of object
    private Context context;

    //constructor for DatabaseHelper class
    public DatabaseHelper(Context context) {
        super(context, Config.DATA_BASE, null, Config.DATA_VERSION);
        this.context = context;
    }

    //creating plant table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_PLANT = "CREATE TABLE " + Config.PLANT_TABLE_NAME +
                " (" + Config.COLUMN_PLANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Config.COLUMN_PLANT_NAME + " TEXT NOT NULL,"
                + Config.COLUMN_PLANT_WATER_INTERVAL + " TEXT NOT NULL,"
                + Config.COLUMN_PLANT_LIGHT_EXPOSURE + " TEXT NOT NULL,"
                + Config.COLUMN_PLANT_TEMPERATURE + " TEXT NOT NULL,"
                + Config.COLUMN_PLANT_GROWTH + " TEXT NOT NULL)";


        sqLiteDatabase.execSQL(CREATE_TABLE_PLANT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    //function to insert plant
    public long insertPlant(PlantProfile plantProfile) {
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Config.COLUMN_PLANT_NAME, plantProfile.getName());
        contentValues.put(Config.COLUMN_PLANT_WATER_INTERVAL, plantProfile.getWaterInterval());
        contentValues.put(Config.COLUMN_PLANT_LIGHT_EXPOSURE, plantProfile.getLightExposure());
        contentValues.put(Config.COLUMN_PLANT_TEMPERATURE, plantProfile.getTemperature());
        contentValues.put(Config.COLUMN_PLANT_GROWTH, plantProfile.getGrowth());

        try {
            id = db.insertOrThrow(Config.PLANT_TABLE_NAME, null, contentValues);
        }
        catch(SQLiteException e) {
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
        return id;
    }

    //function to get all plants
    public List<PlantProfile> getAllPlants() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(Config.PLANT_TABLE_NAME, null, null, null, null, null, null);

            if(cursor != null) {
                if(cursor.moveToFirst()) {
                    List<PlantProfile> plants = new ArrayList<>();

                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_PLANT_ID));
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLANT_NAME));
                        String interval = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLANT_WATER_INTERVAL));
                        String exposure = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLANT_LIGHT_EXPOSURE));
                        String temp = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLANT_TEMPERATURE));
                        String growth = cursor.getString(cursor.getColumnIndex(Config.COLUMN_PLANT_GROWTH));

                        plants.add(new PlantProfile (id, name, interval, exposure, temp, growth));
                    }
                    while(cursor.moveToNext());
                    return plants;
                }
            }
        }
        catch(SQLiteException e) {
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            if(cursor != null)
                cursor.close();
            db.close();
        }
        return Collections.emptyList();
    }
}

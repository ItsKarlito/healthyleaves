package com.example.plantmonitor.UserPlants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.plantmonitor.R;

public class PlantSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_settings);

        getSupportActionBar().setTitle("Plant Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
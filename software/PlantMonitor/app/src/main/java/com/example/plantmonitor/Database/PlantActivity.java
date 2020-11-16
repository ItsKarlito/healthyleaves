package com.example.plantmonitor.Database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.plantmonitor.R;

public class PlantActivity extends AppCompatActivity {

    protected TextView plantName;
    protected TextView plantGrowth;
    protected TextView plantExpo;
    protected TextView plantTemp;
    protected TextView plantInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        plantName = findViewById(R.id.nameTextView);
        plantGrowth = findViewById(R.id.growthTextView);
        plantExpo = findViewById(R.id.exposureTextView);
        plantTemp = findViewById(R.id.tempTextView);
        plantInterval = findViewById(R.id.intervalTextView);

        Intent intent = getIntent();

        plantName.setText("Name: " + intent.getStringExtra("plantName"));
        plantGrowth.setText("Growth(mm): " + intent.getStringExtra("plantGrowth"));
        plantExpo.setText("LightExposure: " + intent.getStringExtra("plantExposure"));
        plantTemp.setText("Temperature(Celcius): " + intent.getStringExtra("plantTemp"));
        plantInterval.setText("Watering Interval(days): " + intent.getStringExtra("plantInterval"));
    }
}
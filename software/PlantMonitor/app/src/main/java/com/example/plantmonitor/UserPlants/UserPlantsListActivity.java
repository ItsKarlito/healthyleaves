package com.example.plantmonitor.UserPlants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.plantmonitor.R;

public class UserPlantsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_plants_list);

        getSupportActionBar().setTitle("User's Plant List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
package com.example.plantmonitor.General;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.plantmonitor.PlantCatalog.PlantCatalogActivity;
import com.example.plantmonitor.R;
import com.example.plantmonitor.UserPlants.UserPlantsListActivity;

public class HomeActivity extends AppCompatActivity {

    Button buttonGoToUserPlantsListActivity;
    Button buttonGoToPlantCatalogActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonGoToUserPlantsListActivity = (Button) findViewById(R.id.buttonGoToUserPlantsListActivity);
        buttonGoToUserPlantsListActivity.setOnClickListener((view) -> {goToUserPlantsListActivity();});
        buttonGoToPlantCatalogActivity = (Button) findViewById(R.id.buttonGoToPlantCatalogActivity);
        buttonGoToPlantCatalogActivity.setOnClickListener((view) -> {goToPlantCatalogActivity();});
    }

    void goToUserPlantsListActivity() {
        Intent intent = new Intent(this, UserPlantsListActivity.class);
        startActivity(intent);
    }

    void goToPlantCatalogActivity() {
        Intent intent = new Intent(this, PlantCatalogActivity.class);
        startActivity(intent);
    }
}
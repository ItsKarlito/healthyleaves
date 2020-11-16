package com.example.plantmonitor.Database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.plantmonitor.PlantCatalog.PlantCatalogActivity;
import com.example.plantmonitor.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListOfPlants extends AppCompatActivity {


    protected ListView plantListView;
    protected Button buttonGoToPlantDatabaseActivity;
    protected FloatingActionButton addPlantFloatingButton;
    protected DatabaseHelper dbHelper = new DatabaseHelper(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToPlantDatabaseActivity = (Button) findViewById(R.id.buttonGoToPlantDatabaseActivity);
        buttonGoToPlantDatabaseActivity.setOnClickListener((view) -> {goToPlantDatabaseActivity();});

        plantListView = findViewById(R.id.plantListView);
        addPlantFloatingButton = findViewById(R.id.addPlantFloatingButton);

        loadListView();

        plantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DatabaseHelper dbHelper = new DatabaseHelper(ListOfPlants.this);
                List<PlantProfile> plantProfiles = dbHelper.getAllPlants();

                String name = plantProfiles.get(position).getName();
                String growth = plantProfiles.get(position).getGrowth();
                String expo = plantProfiles.get(position).getLightExposure();
                String temp = plantProfiles.get(position).getTemperature();
                String interval = plantProfiles.get(position).getWaterInterval();

                Intent intent = new Intent(ListOfPlants.this, PlantActivity.class);
                intent.putExtra("plantName", name);
                intent.putExtra("plantGrowth", growth);
                intent.putExtra("plantExposure", expo);
                intent.putExtra("plantTemp", temp);
                intent.putExtra("plantInterval", interval);

                startActivity(intent);
            }
        });

        //opens fragment

        addPlantFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertFragment dialog = new insertFragment();
                dialog.show(getSupportFragmentManager(), "InsertFragment");
            }
        });
    }

    private void goToPlantDatabaseActivity() {
        Intent intent = new Intent(this, PlantCatalogActivity.class);
        startActivity(intent);
    }

    protected void loadListView() {
        List<PlantProfile> plant = dbHelper.getAllPlants();
        ArrayList<String> plantListText = new ArrayList<>();

        for(int i=0; i<plant.size(); i++) {
            String temp ="";

            temp += plant.get(i).getName();

            plantListText.add(temp);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plantListText);
        plantListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

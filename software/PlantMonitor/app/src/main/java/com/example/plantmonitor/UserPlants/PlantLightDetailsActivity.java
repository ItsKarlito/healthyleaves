package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.plantmonitor.PlantCatalog.Plant;
import com.example.plantmonitor.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.util.ArrayList;

public class PlantLightDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    GraphView lightGraphView;
    LineGraphSeries series;

    ArrayList<Light> lightArray = new ArrayList<Light>();

    ListView lightListView;
    ArrayList<String> lightArrayListString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_light_details);

        //setup GraphView
        lightListView = findViewById(R.id.lightListView);
        lightGraphView = findViewById(R.id.lightGraph);
        series = new LineGraphSeries();
        lightGraphView.addSeries(series);
        lightGraphView.getViewport().setMinY(0.0);
        lightGraphView.getViewport().setMaxY(100.0);
        lightGraphView.getViewport().setYAxisBoundsManual(true);
        lightGraphView.getGridLabelRenderer().setNumHorizontalLabels(10);
        series.setDrawDataPoints(true);
        series.setColor(Color.WHITE);
        series.setDataPointsRadius(5f);
        lightGraphView.getGridLabelRenderer().setVerticalAxisTitle("Light levels %");
        lightGraphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Light");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int sizeOfArray = (int) snapshot.getChildrenCount();
                DataPoint[] dp = new DataPoint[sizeOfArray];
                int index = 0;

                //get all light values in an arraylist
                lightArray.clear();
                lightArrayListString.clear();
                for (DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    Light light = myDataSnapshot.getValue(Light.class);
                    lightArray.add(light);

                    index++;
                    lightArrayListString.add(index + ". Light Levels: " + light.getValue() + "%");
                }

                //bubblesort
                for (int i = 0; i < lightArray.size() - 1; i++) {
                    for (int j = 0; j < lightArray.size() - i - 1; j++) {
                        if (lightArray.get(j).getTime() > lightArray.get(j + 1).getTime()) {
                            Light tempLight = lightArray.get(j);
                            lightArray.set(j, lightArray.get(j + 1));
                            lightArray.set(j + 1, tempLight);
                        }
                    }
                }

                //put data from arraylist in datapoints
                for (int k = 0; k < sizeOfArray; k++) {
                    dp[k] = new DataPoint(lightArray.get(k).getTime(), lightArray.get(k).getValue());
                }
                series.resetData(dp);

                //log data
                ArrayAdapter lightArrayAdapter = new ArrayAdapter(PlantLightDetailsActivity.this,
                        android.R.layout.simple_list_item_1, lightArrayListString);
                lightListView.setAdapter(lightArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Lights", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

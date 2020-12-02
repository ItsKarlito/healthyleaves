package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.plantmonitor.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    ListView lightListView;
    ArrayList<String> lightArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_light_details);

        lightListView = findViewById(R.id.lightListView);
        lightGraphView = findViewById(R.id.lightGraph);
        series = new LineGraphSeries();
        lightGraphView.addSeries(series);
        //lightGraphView.getViewport().setMinY(0.0);
        //lightGraphView.getViewport().setMaxY(100.0);
        //lightGraphView.getViewport().setYAxisBoundsManual(true);
        lightGraphView.getGridLabelRenderer().setNumHorizontalLabels(10);
        series.setDrawDataPoints(true);
        series.setColor(Color.WHITE);
        lightGraphView.getGridLabelRenderer().setVerticalAxisTitle("Light levels %");
        lightGraphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Light");
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;

                for(DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    Light light = myDataSnapshot.getValue(Light.class);
                    dp[index] = new DataPoint(light.getTime(), light.getValue());
                    index++;

                    lightArrayList.add(index + ". Light Levels: " + light.getValue() + "%");
                }
                series.resetData(dp);

                ArrayAdapter lightArrayAdapter = new ArrayAdapter(PlantLightDetailsActivity.this,
                        android.R.layout.simple_list_item_1, lightArrayList);
                lightListView.setAdapter(lightArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

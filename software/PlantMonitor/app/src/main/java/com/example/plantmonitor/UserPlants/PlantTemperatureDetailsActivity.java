package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class PlantTemperatureDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    GraphView temperatureGraphView;
    LineGraphSeries series;

    ListView temperatureListView;
    ArrayList<String> temperatureArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_temperature_details);

        temperatureListView = findViewById(R.id.temperatureListView);
        temperatureGraphView = findViewById(R.id.temperatureGraph);
        series = new LineGraphSeries();
        temperatureGraphView.addSeries(series);
        //temperatureGraphView.getViewport().setMinY(0.0);
        //temperatureGraphView.getViewport().setMaxY(100.0);
        //temperatureGraphView.getViewport().setYAxisBoundsManual(true);
        temperatureGraphView.getGridLabelRenderer().setNumHorizontalLabels(10);
        series.setDrawDataPoints(true);
        series.setColor(Color.GREEN);
        temperatureGraphView.getGridLabelRenderer().setVerticalAxisTitle("Temperature levels %");
        temperatureGraphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Temperature");
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
                    Temperature temperature = myDataSnapshot.getValue(Temperature.class);
                    dp[index] = new DataPoint(temperature.getTime(), temperature.getValue());
                    index++;

                    temperatureArrayList.add(index + ". Temperature Levels: " + temperature.getValue() + "%");
                }
                series.resetData(dp);

                ArrayAdapter temperatureArrayAdapter = new ArrayAdapter(PlantTemperatureDetailsActivity.this,
                        android.R.layout.simple_list_item_1, temperatureArrayList);
                temperatureListView.setAdapter(temperatureArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}



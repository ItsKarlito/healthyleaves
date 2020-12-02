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
import com.google.firebase.database.Query;
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

    ArrayList<Temperature> temperatureArray = new ArrayList<Temperature>();

    ListView temperatureListView;
    ArrayList<String> temperatureArrayListString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_temperature_details);

        temperatureListView = findViewById(R.id.temperatureListView);
        temperatureGraphView = findViewById(R.id.temperatureGraph);
        series = new LineGraphSeries();
        temperatureGraphView.addSeries(series);
        temperatureGraphView.getViewport().setMinY(0.0);
        temperatureGraphView.getViewport().setMaxY(100.0);
        temperatureGraphView.getViewport().setYAxisBoundsManual(true);
        temperatureGraphView.getGridLabelRenderer().setNumHorizontalLabels(10);
        series.setDrawDataPoints(true);
        series.setColor(Color.WHITE);
        series.setDataPointsRadius(5f);
        temperatureGraphView.getGridLabelRenderer().setVerticalAxisTitle("Temperature levels %");
        temperatureGraphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Temperature");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int sizeOfArray = (int) snapshot.getChildrenCount();
                DataPoint[] dp = new DataPoint[sizeOfArray];
                int index = 0;

                //get all temperature values in an arraylist
                temperatureArray.clear();
                temperatureArrayListString.clear();
                for(DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    Temperature temperature = myDataSnapshot.getValue(Temperature.class);
                    temperatureArray.add(temperature);

                    //dp[index] = new DataPoint(light.getTime(), light.getValue());
                    index++;
                    temperatureArrayListString.add(index + ". Temperature Levels: " + temperature.getValue() + "%");
                }

                //bubblesort
                for (int i = 0; i < temperatureArray.size()-1; i++) {
                    for (int j = 0; j < temperatureArray.size()-i-1; j++) {
                        if (temperatureArray.get(j).getTime() > temperatureArray.get(j+1).getTime()) {
                            Temperature tempTemperature = temperatureArray.get(j);
                            temperatureArray.set(j, temperatureArray.get(j+1));
                            temperatureArray.set(j+1, tempTemperature);
                        }
                    }
                }

                //put data from arraylist in datapoints
                for (int k = 0; k < sizeOfArray; k++) {
                    dp[k] = new DataPoint(temperatureArray.get(k).getTime(), temperatureArray.get(k).getValue());
                }
                series.resetData(dp);

                //log data
                ArrayAdapter temperatureArrayAdapter = new ArrayAdapter(PlantTemperatureDetailsActivity.this,
                        android.R.layout.simple_list_item_1, temperatureArrayListString);
                temperatureListView.setAdapter(temperatureArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Temperature", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

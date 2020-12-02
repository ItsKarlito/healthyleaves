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

public class PlantMoistureDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    GraphView moistureGraphView;
    LineGraphSeries series;

    ArrayList<Moisture> moistureArray = new ArrayList<Moisture>();

    ListView moistureListView;
    ArrayList<String> moistureArrayListString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_moisture_details);

        moistureListView = findViewById(R.id.moistureListView);
        moistureGraphView = findViewById(R.id.moistureGraph);
        series = new LineGraphSeries();
        moistureGraphView.addSeries(series);
        moistureGraphView.getViewport().setMinY(0.0);
        moistureGraphView.getViewport().setMaxY(100.0);
        moistureGraphView.getViewport().setYAxisBoundsManual(true);
        moistureGraphView.getGridLabelRenderer().setNumHorizontalLabels(10);
        series.setDrawDataPoints(true);
        series.setColor(Color.WHITE);
        series.setDataPointsRadius(5f);
        moistureGraphView.getGridLabelRenderer().setVerticalAxisTitle("Moisture levels %");
        moistureGraphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Moisture");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int sizeOfArray = (int) snapshot.getChildrenCount();
                DataPoint[] dp = new DataPoint[sizeOfArray];
                int index = 0;

                //get all moisture values in an arraylist
                moistureArray.clear();
                moistureArrayListString.clear();
                for(DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    Moisture moisture = myDataSnapshot.getValue(Moisture.class);
                    moistureArray.add(moisture);

                    //dp[index] = new DataPoint(light.getTime(), light.getValue());
                    index++;
                    moistureArrayListString.add(index + ". Moisture Levels: " + moisture.getValue() + "%");
                }

                //bubblesort
                for (int i = 0; i < moistureArray.size()-1; i++) {
                    for (int j = 0; j < moistureArray.size()-i-1; j++) {
                        if (moistureArray.get(j).getTime() > moistureArray.get(j+1).getTime()) {
                            Moisture tempMoisture = moistureArray.get(j);
                            moistureArray.set(j, moistureArray.get(j+1));
                            moistureArray.set(j+1, tempMoisture);
                        }
                    }
                }

                //put data from arraylist in datapoints
                for (int k = 0; k < sizeOfArray; k++) {
                    dp[k] = new DataPoint(moistureArray.get(k).getTime(), moistureArray.get(k).getValue());
                }
                series.resetData(dp);

                //log data
                ArrayAdapter moistureArrayAdapter = new ArrayAdapter(PlantMoistureDetailsActivity.this,
                        android.R.layout.simple_list_item_1, moistureArrayListString);
                moistureListView.setAdapter(moistureArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Moisture", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

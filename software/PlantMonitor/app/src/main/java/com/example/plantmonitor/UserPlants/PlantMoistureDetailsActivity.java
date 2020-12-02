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

public class PlantMoistureDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    GraphView moistureGraphView;
    LineGraphSeries series;

    ListView moistureListView;
    ArrayList<String> moistureArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_moisture_details);

        moistureListView = findViewById(R.id.moistureListView);
        moistureGraphView = findViewById(R.id.moistureGraph);
        series = new LineGraphSeries();
        moistureGraphView.addSeries(series);
        //moistureGraphView.getViewport().setMinY(0.0);
        //moistureGraphView.getViewport().setMaxY(100.0);
        //moistureGraphView.getViewport().setYAxisBoundsManual(true);
        moistureGraphView.getGridLabelRenderer().setNumHorizontalLabels(10);
        series.setDrawDataPoints(true);
        series.setColor(Color.WHITE);
        moistureGraphView.getGridLabelRenderer().setVerticalAxisTitle("Moisture levels %");
        moistureGraphView.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Moisture");
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
                    Moisture moisture = myDataSnapshot.getValue(Moisture.class);
                    dp[index] = new DataPoint(moisture.getTime(), moisture.getValue());
                    index++;

                    moistureArrayList.add(index + ". Moisture Levels: " + moisture.getValue() + "%");
                }
                series.resetData(dp);

                ArrayAdapter moistureArrayAdapter = new ArrayAdapter(PlantMoistureDetailsActivity.this,
                        android.R.layout.simple_list_item_1, moistureArrayList);
                moistureListView.setAdapter(moistureArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
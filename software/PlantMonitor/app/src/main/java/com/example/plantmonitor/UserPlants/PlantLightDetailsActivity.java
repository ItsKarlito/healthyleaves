package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.plantmonitor.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class PlantLightDetailsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    LineChart lightChart;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;
    ListView lightListView;
    ArrayList<String> lightArrayList = new ArrayList<>();

    int currentLight = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_light_details);

        lightChart = findViewById(R.id.lightChart);
        lightListView = findViewById(R.id.lightListView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Light");

        retrieveData();
    }

    private void retrieveData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> dataVals = new ArrayList<Entry>();

                if(dataSnapshot.hasChildren()){
                    int currentTime = -1;
                    for(DataSnapshot  myDataSnapShot : dataSnapshot.getChildren()) {
                        Light light = myDataSnapShot.getValue(Light.class);
                        if(light.getTime() > currentTime) {
                            currentLight = light.getValue();
                        }
                            dataVals.add(new Entry(light.getTime(), currentLight));

                            lightArrayList.add("Light Levels: " + currentLight + "%");
                    }
                    showChart(dataVals);

                    ArrayAdapter lightArrayAdapter = new ArrayAdapter(PlantLightDetailsActivity.this,
                            android.R.layout.simple_list_item_1, lightArrayList);
                    lightListView.setAdapter(lightArrayAdapter);
                }
                else {
                    lightChart.clear();
                    lightChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("History of light levels");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        lightChart.clear();
        lightChart.setData(lineData);
        lightChart.invalidate();
    }
}





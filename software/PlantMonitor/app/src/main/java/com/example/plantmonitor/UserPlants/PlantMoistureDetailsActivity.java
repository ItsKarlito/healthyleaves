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

public class PlantMoistureDetailsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    LineChart moistureChart;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;
    ListView moistureListView;
    ArrayList<String> moistureArrayList = new ArrayList<>();

    int currentMoisture = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_moisture_details);

        moistureChart = findViewById(R.id.moistureChart);
        moistureListView = findViewById(R.id.moistureListView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Moisture");

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
                        Moisture moisture = myDataSnapShot.getValue(Moisture.class);
                        if(moisture.getTime() > currentTime) {
                            currentMoisture = moisture.getValue();
                        }
                        dataVals.add(new Entry(moisture.getTime(), currentMoisture));

                        moistureArrayList.add("Moisture Levels: " + currentMoisture + "%");
                    }
                    showChart(dataVals);

                    ArrayAdapter moistureArrayAdapter = new ArrayAdapter(PlantMoistureDetailsActivity.this,
                            android.R.layout.simple_list_item_1, moistureArrayList);
                    moistureListView.setAdapter(moistureArrayAdapter);
                }
                else {
                    moistureChart.clear();
                    moistureChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("History of moisture levels");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        moistureChart.clear();
        moistureChart.setData(lineData);
        moistureChart.invalidate();
    }
}
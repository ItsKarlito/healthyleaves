package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.List;

public class PlantTemperatureDetailsActivity extends AppCompatActivity {

    /*final String TAG = PlantTemperatureDetailsActivity.class.getSimpleName();

    final String refChild = "Temperature";
    List<Data> data;*/

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    LineChart temperatureChart;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;
    ListView temperatureListView;
    ArrayList<String> temperatureArrayList = new ArrayList<>();

    int currentTemperature = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_temperature_details);

        /*DatabaseReference database = FirebaseDatabase.getInstance().getReference(refChild);
        database.addListenerForSingleValueEvent(ref);
        Toast.makeText(this, data.get(0).getUserPlantID(), Toast.LENGTH_LONG).show();
    }

    ValueEventListener ref = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            for (DataSnapshot snap_child : snapshot.getChildren()) {
                data.add(new Data(snap_child.child("time").getValue(Integer.class),
                        snap_child.child("userPlantId").getValue(Integer.class),
                        snap_child.child("value").getValue(Integer.class)));
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    };*/

        temperatureChart = findViewById(R.id.temperatureChart);
        temperatureListView = findViewById(R.id.temperatureListView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Temperature");

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
                        Temperature temperature = myDataSnapShot.getValue(Temperature.class);
                        if(temperature.getTime() > currentTime) {
                            currentTemperature = temperature.getValue();
                        }
                            dataVals.add(new Entry(temperature.getTime(), currentTemperature));

                            temperatureArrayList.add("Temperature Levels: " + currentTemperature + "%");
                    }
                    showChart(dataVals);

                    ArrayAdapter temperatureArrayAdapter = new ArrayAdapter(PlantTemperatureDetailsActivity.this,
                            android.R.layout.simple_list_item_1, temperatureArrayList);
                    temperatureListView.setAdapter(temperatureArrayAdapter);
                }
                else {
                    temperatureChart.clear();
                    temperatureChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("History of temperature levels");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        temperatureChart.clear();
        temperatureChart.setData(lineData);
        temperatureChart.invalidate();
    }

}




























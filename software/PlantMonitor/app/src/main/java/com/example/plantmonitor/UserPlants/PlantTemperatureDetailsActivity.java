package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.plantmonitor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PlantTemperatureDetailsActivity extends AppCompatActivity {

    final String TAG = PlantTemperatureDetailsActivity.class.getSimpleName();

    final String refChild = "Temperature";
    List<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_temperature_details);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference(refChild);
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
    };

}
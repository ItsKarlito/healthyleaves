package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantmonitor.PlantCatalog.Plant;
import com.example.plantmonitor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PlantProfileActivity extends AppCompatActivity {

    TextView textViewUserPlantName;
    TextView textViewUserPlantType;
    Button buttonGoToPlantLightDetailsActivity;
    Button buttonGoToPlantMoistureDetailsActivity;
    Button buttonGoToPlantTemperatureDetailsActivity;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceLight;
    private DatabaseReference databaseReferenceMoisture;
    private DatabaseReference databaseReferenceTemperature;

    private Bundle bundle;

    int currentLight = -1;
    int currentMoisture = -1;
    int currentTemperature = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        getSupportActionBar().setTitle("Plant Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewUserPlantName = (TextView) findViewById(R.id.textViewUserPlantName);
        textViewUserPlantType = (TextView) findViewById(R.id.textViewUserPlantType);
        buttonGoToPlantLightDetailsActivity = (Button) findViewById(R.id.buttonGoToPlantLightDetailsActivity);
        buttonGoToPlantMoistureDetailsActivity = (Button) findViewById(R.id.buttonGoToPlantMoistureDetailsActivity);
        buttonGoToPlantTemperatureDetailsActivity = (Button) findViewById(R.id.buttonGoToPlantTemperatureDetailsActivity);

        showInformation();
    }

    private void showInformation() {
        bundle = getIntent().getExtras();

        String tempUserPlantID = bundle.getString("userPlantID");
        getValues(tempUserPlantID);

        String tempUserPlantName = bundle.getString("userPlantName");
        String tempPlantID = bundle.getString("plantID");
        String tempPlantName = bundle.getString("plantName");
        textViewUserPlantName.setText(tempUserPlantName);
        textViewUserPlantType.setText(tempPlantName);

    }

    private void getValues(String tempUserPlantID) {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        Query latestLight = databaseReference.child("Light").orderByChild("time");
        latestLight.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int currentTime = -1;
                for(DataSnapshot child : snapshot.getChildren()) {
                    Light light = child.getValue(Light.class);
                    if(light.getTime() > currentTime) {
                        currentLight = light.getValue();
                    }
                }

                Integer tempPlantIdealLight = bundle.getInt("plantIdealLight");
                buttonGoToPlantLightDetailsActivity.setText(
                        "Current Light: " + Integer.toString(currentLight) +
                                "\nIdeal Light: " + Integer.toString(tempPlantIdealLight)
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Light", Toast.LENGTH_SHORT).show();
            }
        });


        Query latestMoisture = databaseReference.child("Moisture").orderByChild("time");
        latestMoisture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int currentTime = -1;
                for(DataSnapshot child : snapshot.getChildren()) {
                    Moisture moisture = child.getValue(Moisture.class);
                    if(moisture.getTime() > currentTime) {
                        currentMoisture = moisture.getValue();
                    }
                }

                Integer tempPlantIdealMoisture = bundle.getInt("plantIdealMoisture");
                buttonGoToPlantMoistureDetailsActivity.setText(
                        "Current Moisture: " + Integer.toString(currentMoisture) +
                                "\nIdeal Moisture: " + Integer.toString(tempPlantIdealMoisture)
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Moisture", Toast.LENGTH_SHORT).show();
            }
        });

        Query latestTemperature = databaseReference.child("Temperature").orderByChild("time").limitToFirst(10);
        latestTemperature.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int currentTime = -1;
                for(DataSnapshot child : snapshot.getChildren()) {
                    Temperature temperature = child.getValue(Temperature.class);
                    if (temperature.getTime() > currentTime) {
                        currentTemperature = temperature.getValue();
                    }
                }

                Integer tempPlantIdealTemperature = bundle.getInt("plantIdealTemperature");
                buttonGoToPlantTemperatureDetailsActivity.setText(
                        "Current Temperature: " + Integer.toString(currentTemperature) +
                                "\nIdeal Temperature: " + Integer.toString(tempPlantIdealTemperature)
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Temperature", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
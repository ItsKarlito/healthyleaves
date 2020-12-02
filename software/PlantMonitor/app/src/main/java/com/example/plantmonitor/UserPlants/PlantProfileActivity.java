package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    TextView textViewPlantLightDetails;
    TextView textViewPlantMoistureDetails;
    TextView textViewPlantTemperatureDetails;

    TextView textViewPlantLightIdeal;
    TextView textViewPlantMoistureIdeal;
    TextView textViewPlantTemperatureIdeal;

    TextView textViewPlantLightRecommendations;
    TextView textViewPlantMoistureRecommendations;
    TextView textViewPlantTemperatureRecommendations;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceLight;
    private DatabaseReference databaseReferenceMoisture;
    private DatabaseReference databaseReferenceTemperature;

    private Bundle bundle;

    String tempUserPlantID;
    String tempDeviceID;

    int currentLight = -1;
    int currentMoisture = -1;
    int currentTemperature = -1;

    int tempPlantIdealLight;
    int tempPlantIdealMoisture;
    int tempPlantIdealTemperature;

    String tempLightRecommendations;
    String tempMoistureRecommendations;
    String tempTemperatureRecommendations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        textViewUserPlantName = (TextView) findViewById(R.id.textViewUserPlantName);
        textViewUserPlantType = (TextView) findViewById(R.id.textViewUserPlantType);
        textViewPlantLightDetails = (TextView) findViewById(R.id.textViewPlantLightDetails);
        textViewPlantMoistureDetails = (TextView) findViewById(R.id.textViewPlantMoistureDetails);
        textViewPlantTemperatureDetails = (TextView) findViewById(R.id.textViewPlantTemperatureDetails);
        textViewPlantLightIdeal = (TextView) findViewById(R.id.textViewPlantLightIdeal);
        textViewPlantMoistureIdeal = (TextView) findViewById(R.id.textViewPlantMoistureIdeal);
        textViewPlantTemperatureIdeal = (TextView) findViewById(R.id.textViewPlantTemperatureIdeal);

        textViewPlantLightRecommendations = (TextView) findViewById(R.id.textViewPlantLightRecommendations);
        textViewPlantMoistureRecommendations = (TextView) findViewById(R.id.textViewPlantMoistureRecommendations);
        textViewPlantTemperatureRecommendations = (TextView) findViewById(R.id.textViewPlantTemperatureRecommendations);

        showInformation();
    }

    private void showInformation() {
        bundle = getIntent().getExtras();

        String tempUserPlantName = bundle.getString("userPlantName");
        textViewUserPlantName.setText(tempUserPlantName);

        String tempPlantID = bundle.getString("plantID");

        String tempPlantName = bundle.getString("plantName");
        textViewUserPlantType.setText(tempPlantName);
        textViewUserPlantName.setOnClickListener((view) -> {goToUserPlantsListActivity();});

        tempPlantIdealLight = bundle.getInt("plantIdealLight");
        textViewPlantLightIdeal.setText("(ideal: " + tempPlantIdealLight + "%)");

        tempPlantIdealMoisture = bundle.getInt("plantIdealMoisture");
        textViewPlantMoistureIdeal.setText("(ideal: " + tempPlantIdealMoisture + "%)");

        tempPlantIdealTemperature = bundle.getInt("plantIdealTemperature");
        textViewPlantTemperatureIdeal.setText("(ideal: " + tempPlantIdealTemperature + "°C)");

        tempUserPlantID = bundle.getString("userPlantID");
        tempDeviceID = bundle.getString("deviceID");

        getValues(tempUserPlantID, tempDeviceID);
    }

    private void getValues(String tempUserPlantID, String tempDeviceID) {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        Query latestLight = databaseReference.child("Light").orderByChild("time").limitToLast(10);
        latestLight.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int currentTime = -1;
                for(DataSnapshot child : snapshot.getChildren()) {
                    Light light = child.getValue(Light.class);
                    if(light.getUserPlantId().equals(tempUserPlantID) || light.getUserPlantId().equals(tempDeviceID)) {
                        if(light.getTime() > currentTime) {
                            currentLight = light.getValue();
                        }
                    }
                }

                if(currentLight != -1) {
                    textViewPlantLightDetails.setText(currentLight + "%");
                    textViewPlantLightDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent (PlantProfileActivity.this, PlantLightDetailsActivity.class);
                            startActivity(intent);
                        }
                    });

                    if (currentLight < tempPlantIdealLight - 5) {
                        tempLightRecommendations = "move your plant to a more sunny location.";
                    }
                    else if (currentLight >  tempPlantIdealLight + 5) {
                        tempLightRecommendations = "move plant to a location with more shade.";
                    }
                    textViewPlantLightRecommendations.setText(tempLightRecommendations);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Light", Toast.LENGTH_SHORT).show();
            }
        });


        Query latestMoisture = databaseReference.child("Moisture").orderByChild("time").limitToLast(10);
        latestMoisture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int currentTime = -1;
                for(DataSnapshot child : snapshot.getChildren()) {
                    Moisture moisture = child.getValue(Moisture.class);
                    if(moisture.getUserPlantId().equals(tempUserPlantID) || moisture.getUserPlantId().equals(tempDeviceID)) {
                        if (moisture.getTime() > currentTime) {
                            currentMoisture = moisture.getValue();
                        }
                    }
                }

                if (currentMoisture != -1) {
                    textViewPlantMoistureDetails.setText(currentMoisture + "%");
                    textViewPlantMoistureDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent (PlantProfileActivity.this, PlantMoistureDetailsActivity.class);
                            startActivity(intent);
                        }
                    });

                    if (currentMoisture < tempPlantIdealMoisture - 5) {
                        tempMoistureRecommendations = "water your plant more often.";
                    }
                    else if (currentMoisture >  tempPlantIdealMoisture + 5) {
                        tempMoistureRecommendations = "reduce the amount of water you're pouring.";
                    }
                    textViewPlantMoistureRecommendations.setText(tempMoistureRecommendations);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Moisture", Toast.LENGTH_SHORT).show();
            }
        });

        Query latestTemperature = databaseReference.child("Temperature").orderByChild("time").limitToLast(10);
        latestTemperature.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int currentTime = -1;
                for(DataSnapshot child : snapshot.getChildren()) {
                    Temperature temperature = child.getValue(Temperature.class);
                    if (temperature.getUserPlantId().equals(tempUserPlantID) || temperature.getUserPlantId().equals(tempDeviceID)) {
                        if (temperature.getTime() > currentTime) {
                            currentTemperature = temperature.getValue();
                        }
                    }
                }

                if (currentTemperature != -1) {
                    textViewPlantTemperatureDetails.setText(currentTemperature + "°C");
                    textViewPlantTemperatureDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent (PlantProfileActivity.this, PlantTemperatureDetailsActivity.class);
                            startActivity(intent);
                        }
                    });

                    if (currentTemperature < tempPlantIdealTemperature - 5) {
                        tempTemperatureRecommendations = "move your plant to a warmer environment.";
                    }
                    else if (currentTemperature >  tempPlantIdealTemperature + 5) {
                        tempTemperatureRecommendations = "move your plant to a cooler environment.";
                    }
                    textViewPlantTemperatureRecommendations.setText(tempTemperatureRecommendations);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Temperature", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void goToUserPlantsListActivity() {
        Intent intent = new Intent(this, UserPlantsListActivity.class);
        startActivity(intent);
    }
}
package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantmonitor.PlantCatalog.Plant;
import com.example.plantmonitor.R;
import com.google.firebase.auth.FirebaseAuth;
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
    private DatabaseReference databaseReferenceLight;
    private DatabaseReference databaseReferenceMoisture;
    private DatabaseReference databaseReferenceTemperature;

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
        Bundle bundle = getIntent().getExtras();
        String tempUserPlantID = bundle.getString("userPlantID");
        getCurrentLight(tempUserPlantID);
        String tempUserPlantName = bundle.getString("userPlantName");
        String tempPlantID = bundle.getString("plantID");
        String tempPlantName = bundle.getString("plantName");

        Integer tempPlantIdealLight = bundle.getInt("plantIdealLight");
        Integer tempPlantIdealMoisture = bundle.getInt("plantIdealMoisture");
        Integer tempPlantIdealTemperature = bundle.getInt("plantIdealTemperature");

        textViewUserPlantName.setText(tempUserPlantName);
        textViewUserPlantType.setText(tempPlantName);

        buttonGoToPlantLightDetailsActivity.setText(
                "Current Light: " + Integer.toString(currentLight) +
                        "\nIdeal Light: " + Integer.toString(tempPlantIdealLight)
        );
        buttonGoToPlantMoistureDetailsActivity.setText(
                "Current Moisture: " + Integer.toString(currentMoisture) +
                        "\nIdeal Moisture: " + Integer.toString(tempPlantIdealMoisture)
        );
        buttonGoToPlantTemperatureDetailsActivity.setText(
                "Current Temperature: " + Integer.toString(currentTemperature) +
                        "\nIdeal Temperature: " + Integer.toString(tempPlantIdealTemperature)
        );
    }

    private void getCurrentLight(String tempUserPlantID) {
        database = FirebaseDatabase.getInstance();
        databaseReferenceLight = database.getReference("Light");
        Query latestLight = databaseReferenceLight.orderByChild("time").limitToLast(1);
        latestLight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int mostRecentTime = 0;
                for(DataSnapshot child : snapshot.getChildren()) {
                    //currentLight = child.child("value").getValue();
                    Light light = child.getValue(Light.class);
                    //if (light.getUserPlantId().equals(tempUserPlantID)) {
                        if(light.getTime() > mostRecentTime) {
                            currentLight = light.getValue();
                        }
                    //}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to load Light", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCurrentMoisture(String tempUserPlantID) {
        database = FirebaseDatabase.getInstance();
        databaseReferenceMoisture = database.getReference("Moisture");
    }
    private void getCurrentTemperature(String tempUserPlantID) {
        database = FirebaseDatabase.getInstance();
        databaseReferenceTemperature = database.getReference("Temperature");
    }
}
package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantmonitor.PlantCatalog.Plant;
import com.example.plantmonitor.PlantCatalog.PlantCatalogActivity;
import com.example.plantmonitor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddUserPlantActivity extends AppCompatActivity {

    private String TAG = "AddUserPlantActivity";

    private DatabaseReference databasePlants;
    private DatabaseReference databaseOwnsA;

    String plantID = null;

    TextView textViewUserID = null;
    EditText editTextUserPlantName = null;
    TextView textViewPlantName = null;
    TextView textViewPlantIdealLight = null;
    TextView textViewPlantIdealMoisture = null;
    TextView textViewPlantIdealTemperature = null;
    TextView textViewPlantDescription = null;
    Button buttonAddUserPlant = null;
    Button buttonCancelPlantDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_plant);

        getSupportActionBar().setTitle("Pairing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databasePlants = FirebaseDatabase.getInstance().getReference("Plants");
        databaseOwnsA = FirebaseDatabase.getInstance().getReference("OwnsA");

        textViewUserID = (TextView) findViewById(R.id.textViewUserID);
        editTextUserPlantName = (EditText) findViewById(R.id.editTextUserPlantName);
        textViewPlantName = (TextView) findViewById(R.id.textViewPlantName);
        textViewPlantIdealLight = (TextView) findViewById(R.id.textViewPlantIdealLight);
        textViewPlantIdealMoisture = (TextView) findViewById(R.id.textViewPlantIdealMoisture);
        textViewPlantIdealTemperature = (TextView) findViewById(R.id.textViewPlantIdealTemperature);
        textViewPlantDescription = (TextView) findViewById(R.id.textViewPlantDescription);

        Bundle bundle = getIntent().getExtras();
        String tempPlantName = bundle.getString("plantName");
        databasePlants.orderByChild("plantName").equalTo(tempPlantName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    plantID = childSnapshot.getKey();
                    textViewPlantName.setText("Plant Type: " + childSnapshot.child("plantName").getValue(String.class));
                    textViewPlantIdealLight.setText("Ideal Light: " + Integer.toString(childSnapshot.child("plantIdealLight").getValue(Integer.class))  + "%");
                    textViewPlantIdealMoisture.setText("Ideal Moisture: " + Integer.toString(childSnapshot.child("plantIdealMoisture").getValue(Integer.class)) + "%");
                    textViewPlantIdealTemperature.setText("Ideal Temperature: " + Integer.toString(childSnapshot.child("plantIdealTemperature").getValue(Integer.class)) + "*C");
                    textViewPlantDescription.setText("Description: " + childSnapshot.child("plantDescription").getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "listener for single value event cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        textViewUserID.setText("2JH5J2K4_5JK3-2JK3H45");

        buttonAddUserPlant = (Button) findViewById(R.id.buttonAddUserPlant);
        buttonAddUserPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: fetch userID of logged in user
                String userID = "2JH5J2K4_5JK3-2JK3H45";
                String name = editTextUserPlantName.getText().toString();

                OwnsA newUserPlant = new OwnsA(name, plantID, userID);
                String key = databaseOwnsA.push().getKey();
                databaseOwnsA.child(key).setValue(newUserPlant);

                Log.d(TAG, newUserPlant.toString());
                Toast.makeText(getApplicationContext(), "succesful user plant entry", Toast.LENGTH_SHORT).show();
                goToUserPlantListActivity();
            }
        });

        buttonCancelPlantDatabase = (Button) findViewById(R.id.buttonCancelPlantDatabase);
        buttonCancelPlantDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "cancelled user plant entry", Toast.LENGTH_SHORT).show();
                goToUserPlantListActivity();
            }
        });
    }

    void goToUserPlantListActivity() {
        Intent intent = new Intent(this, UserPlantsListActivity.class);
        startActivity(intent);
        //finish()
    }
}
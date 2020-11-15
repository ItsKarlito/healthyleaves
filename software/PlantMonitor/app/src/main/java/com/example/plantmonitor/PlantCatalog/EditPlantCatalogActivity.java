package com.example.plantmonitor.PlantCatalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plantmonitor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditPlantCatalogActivity extends AppCompatActivity {
    private String TAG = "EditPlantCatalogActivity";

    private DatabaseReference databasePlants;

    String plantKey = null;

    EditText editTextPlantName = null;
    EditText editTextPlantIdealLight = null;
    EditText editTextPlantIdealMoisture = null;
    EditText editTextPlantIdealTemperature = null;
    EditText editTextPlantDescription = null;
    Button buttonSavePlantDatabase = null;
    Button buttonCancelPlantDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plant_catalog);

        databasePlants = FirebaseDatabase.getInstance().getReference("Plants");

        editTextPlantName = (EditText) findViewById(R.id.editTextPlantName);
        editTextPlantIdealLight = (EditText) findViewById(R.id.editTextPlantIdealLight);
        editTextPlantIdealMoisture = (EditText) findViewById(R.id.editTextPlantIdealMoisture);
        editTextPlantIdealTemperature = (EditText) findViewById(R.id.editTextPlantIdealTemperature);
        editTextPlantDescription = (EditText) findViewById(R.id.editTextPlantDescription);

        Bundle bundle = getIntent().getExtras();
        String tempPlantName = bundle.getString("plantName");
        databasePlants.orderByChild("plantName").equalTo(tempPlantName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    plantKey = childSnapshot.getKey();
                    editTextPlantName.setText(childSnapshot.child("plantName").getValue(String.class));
                    editTextPlantIdealLight.setText(Integer.toString(childSnapshot.child("plantIdealLight").getValue(Integer.class)));
                    editTextPlantIdealMoisture.setText(Integer.toString(childSnapshot.child("plantIdealMoisture").getValue(Integer.class)));
                    editTextPlantIdealTemperature.setText(Integer.toString(childSnapshot.child("plantIdealTemperature").getValue(Integer.class)));
                    editTextPlantDescription.setText(childSnapshot.child("plantDescription").getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "listener for single value event cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSavePlantDatabase = (Button) findViewById(R.id.buttonSavePlantDatabase);
        buttonSavePlantDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidity()) {
                    String plantName = editTextPlantName.getText().toString();
                    int plantIdealLight = Integer.parseInt(editTextPlantIdealLight.getText().toString());
                    int plantIdealMoisture = Integer.parseInt(editTextPlantIdealMoisture.getText().toString());
                    int plantIdealTemperature = Integer.parseInt(editTextPlantIdealTemperature.getText().toString());
                    String plantDescription = editTextPlantDescription.getText().toString();

                    Plant newPlant = new Plant(plantName, plantIdealLight, plantIdealMoisture, plantIdealTemperature, plantDescription);
                    databasePlants.child(plantKey).setValue(newPlant);

                    Log.d(TAG, newPlant.toString());
                    Toast.makeText(getApplicationContext(), "succesful plant entry", Toast.LENGTH_SHORT).show();
                    goToPlantCatalogActivity();
                }
            }
        });

        buttonCancelPlantDatabase = (Button) findViewById(R.id.buttonCancelPlantDatabase);
        buttonCancelPlantDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "cancelled plant entry", Toast.LENGTH_SHORT).show();
                goToPlantCatalogActivity();
            }
        });
    }

    void goToPlantCatalogActivity() {
        Intent intent = new Intent(this, PlantCatalogActivity.class);
        startActivity(intent);
        //finish()
    }

    private boolean checkValidity() {
        //check for empty fields
        if(editTextPlantName.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "NAME WAS LEFT EMPTY", Toast.LENGTH_LONG).show();
            return false;
        }
        if(editTextPlantIdealLight.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "LIGHT WAS LEFT EMPTY", Toast.LENGTH_LONG).show();
            return false;
        }
        if(editTextPlantIdealMoisture.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "MOISTURE WAS LEFT EMPTY", Toast.LENGTH_LONG).show();
            return false;
        }
        if(editTextPlantIdealTemperature.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "TEMPERATURE WAS LEFT EMPTY", Toast.LENGTH_LONG).show();
            return false;
        }
        if(editTextPlantDescription.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "DESCRIPTION WAS LEFT EMPTY", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
package com.example.plantmonitor.PlantCatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plantmonitor.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlantCatalogActivity extends AppCompatActivity {

    private String TAG = "AddPlantCatalogActivity";

    private DatabaseReference databasePlants;

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
        setContentView(R.layout.activity_add_plant_catalog);

        databasePlants = FirebaseDatabase.getInstance().getReference("Plants");

        editTextPlantName = (EditText) findViewById(R.id.editTextPlantName);
        editTextPlantIdealLight = (EditText) findViewById(R.id.editTextPlantIdealLight);
        editTextPlantIdealMoisture = (EditText) findViewById(R.id.editTextPlantIdealMoisture);
        editTextPlantIdealTemperature = (EditText) findViewById(R.id.editTextPlantIdealTemperature);
        editTextPlantDescription = (EditText) findViewById(R.id.editTextPlantDescription);


        buttonSavePlantDatabase = (Button) findViewById(R.id.buttonSavePlantDatabase);
        buttonSavePlantDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidity()) {
                    String plantName = editTextPlantName.getText().toString();
                    int plantIdealLight = Integer.parseInt(editTextPlantIdealLight.getText().toString());
                    int plantIdealMoisture = Integer.parseInt(editTextPlantIdealMoisture.getText().toString());
                    int plantIdealTemperature = Integer.parseInt(editTextPlantIdealTemperature.getText().toString());
                    String plantDescription = editTextPlantDescription.getText().toString();

                    Plant newPlant = new Plant(plantName,plantIdealLight,plantIdealMoisture,plantIdealTemperature,plantDescription);
                    String key = databasePlants.push().getKey();
                    databasePlants.child(key).setValue(newPlant);

                    Log.d(TAG, newPlant.toString());
                    Toast.makeText(getApplicationContext(), "succesful plant entry", Toast.LENGTH_SHORT).show();
                    goToPlantDatabaseActivity();
                }
            }
        });

        buttonCancelPlantDatabase = (Button) findViewById(R.id.buttonCancelPlantDatabase);
        buttonCancelPlantDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "cancelled plant entry", Toast.LENGTH_SHORT).show();
                goToPlantDatabaseActivity();
            }
        });
    }

    void goToPlantDatabaseActivity() {
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
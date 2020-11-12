package com.example.plantmonitor.UserPlants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.plantmonitor.R;

import org.w3c.dom.Text;

public class PlantProfileActivity extends AppCompatActivity {

    TextView textViewUserPlantName;
    TextView textViewUserPlantCurrentLight;
    TextView textViewUserPlantCurrentMoisture;
    TextView textViewUserPlantCurrentTemperature;
    TextView textViewUserPlantType;
    TextView textViewUserPlantIdealLight;
    TextView textViewUserPlantIdealMoisture;
    TextView textViewUserPlantIdealTemperature;
    Button buttonGoToPlantLightDetailsActivity;
    Button buttonGoToPlantMoistureDetailsActivity;
    Button buttonGoToPlantTemperatureDetailsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_profile);

        getSupportActionBar().setTitle("Plant Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewUserPlantName = (TextView) findViewById(R.id.textViewUserPlantName);
        textViewUserPlantCurrentLight = (TextView) findViewById(R.id.textViewUserPlantCurrentLight);
        textViewUserPlantCurrentMoisture = (TextView) findViewById(R.id.textViewUserPlantCurrentMoisture);
        textViewUserPlantCurrentTemperature = (TextView) findViewById(R.id.textViewUserPlantCurrentTemperature);
        textViewUserPlantType = (TextView) findViewById(R.id.textViewUserPlantType);
        textViewUserPlantIdealLight = (TextView) findViewById(R.id.textViewUserPlantIdealLight);
        textViewUserPlantIdealMoisture = (TextView) findViewById(R.id.textViewUserPlantIdealMoisture);
        textViewUserPlantIdealTemperature = (TextView) findViewById(R.id.textViewUserPlantIdealTemperature);
        buttonGoToPlantLightDetailsActivity = (Button) findViewById(R.id.buttonGoToPlantLightDetailsActivity);
        buttonGoToPlantMoistureDetailsActivity = (Button) findViewById(R.id.buttonGoToPlantMoistureDetailsActivity);
        buttonGoToPlantTemperatureDetailsActivity = (Button) findViewById(R.id.buttonGoToPlantTemperatureDetailsActivity);

        Bundle bundle = getIntent().getExtras();
        String tempUserPlantID = bundle.getString("userPlantID");
        String tempUserPlantName = bundle.getString("userPlantName");
        String tempPlantID = bundle.getString("plantID");
        String tempPlantName = bundle.getString("plantName");
        Integer tempPlantIdealLight = bundle.getInt("plantIdealLight");
        Integer tempPlantIdealMoisture = bundle.getInt("plantIdealMoisture");
        Integer tempPlantIdealTemperature = bundle.getInt("plantIdealTemperature");

        textViewUserPlantName.setText(tempUserPlantName);
        textViewUserPlantCurrentLight.setText("Current Light: " + Integer.toString(tempPlantIdealLight));
        textViewUserPlantCurrentMoisture.setText("Current Moisture: " + Integer.toString(tempPlantIdealMoisture));
        textViewUserPlantCurrentTemperature.setText("Current Temperature: " + Integer.toString(tempPlantIdealTemperature));
        textViewUserPlantType.setText("Plant Type: " + tempPlantName);
        textViewUserPlantIdealLight.setText("Ideal Light: " + Integer.toString(tempPlantIdealLight));
        textViewUserPlantIdealMoisture.setText("Ideal Moisture: " + Integer.toString(tempPlantIdealMoisture));
        textViewUserPlantIdealTemperature.setText("Ideal Temperature: " + Integer.toString(tempPlantIdealTemperature));
    }
}
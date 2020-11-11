package com.example.plantmonitor.PlantCatalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmonitor.R;
import com.example.plantmonitor.UserPlants.AddUserPlantActivity;

import java.util.ArrayList;

public class MyAdapterCatalog extends RecyclerView.Adapter<MyAdapterCatalog.MyViewHolder> {

    private String TAG = "MyAdapterCatalog";

    ArrayList<Plant> plantArray;
    Context context;

    public MyAdapterCatalog(Context context, ArrayList<Plant> plantArray){
        this.context = context;
        this.plantArray = plantArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_plant, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Plant plant = plantArray.get(position);
        if (plant != null) {
            holder.textViewPlantName.setText(plant.getPlantName());
            holder.textViewPlantIdealLight.setText("Ideal Light: " + Integer.toString(plant.getPlantIdealLight()) + "%");
            holder.textViewPlantIdealMoisture.setText("Ideal Moisture: " + Integer.toString(plant.getPlantIdealMoisture()) + "%");
            holder.textViewPlantIdealTemperature.setText("Ideal Temperature: " + Integer.toString(plant.getPlantIdealTemperature()) + "*C");
            holder.textViewPlantDescription.setText(plant.getPlantDescription());
            holder.buttonEditPlantCatalog.setOnClickListener((view) -> {goToEditPlantCatalogActivity(view, plant.getPlantName());});
            holder.buttonAddUserPlant.setOnClickListener((view) -> {goToAddUserPlantActivity(view, plant.getPlantName());});
        }
        Log.d(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return plantArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPlantName;
        TextView textViewPlantDescription;
        TextView textViewPlantIdealLight;
        TextView textViewPlantIdealMoisture;
        TextView textViewPlantIdealTemperature;
        Button buttonEditPlantCatalog = null;
        Button buttonAddUserPlant = null;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPlantName = itemView.findViewById(R.id.textViewPlantName);
            textViewPlantDescription = itemView.findViewById(R.id.textViewPlantDescription);
            textViewPlantIdealLight = itemView.findViewById(R.id.textViewPlantIdealLight);
            textViewPlantIdealMoisture = itemView.findViewById(R.id.textViewPlantIdealMoisture);
            textViewPlantIdealTemperature = itemView.findViewById(R.id.textViewPlantIdealTemperature);
            buttonEditPlantCatalog = itemView.findViewById(R.id.buttonEditPlantCatalog);
            buttonAddUserPlant = itemView.findViewById(R.id.buttonAddUserPlant);
        }
    }

    void goToEditPlantCatalogActivity(View view, String plantName) {
        Intent intent = new Intent(context, EditPlantCatalogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putString("plantName", plantName);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }

    void goToAddUserPlantActivity(View view, String plantName) {
        Intent intent = new Intent(context, AddUserPlantActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putString("plantName", plantName);
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }
}

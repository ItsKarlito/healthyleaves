package com.example.plantmonitor.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmonitor.PlantCatalog.Plant;
import com.example.plantmonitor.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private String TAG = "MyAdapter";

    ArrayList<Plant> plantArray;
    Context context;

    public MyAdapter(Context context, ArrayList<Plant> plantArray){
        this.context = context;
        this.plantArray = plantArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPlantName = itemView.findViewById(R.id.textViewPlantName);
            textViewPlantDescription = itemView.findViewById(R.id.textViewPlantDescription);
            textViewPlantIdealLight = itemView.findViewById(R.id.textViewPlantIdealLight);
            textViewPlantIdealMoisture = itemView.findViewById(R.id.textViewPlantIdealMoisture);
            textViewPlantIdealTemperature = itemView.findViewById(R.id.textViewPlantIdealTemperature);
        }
    }
}

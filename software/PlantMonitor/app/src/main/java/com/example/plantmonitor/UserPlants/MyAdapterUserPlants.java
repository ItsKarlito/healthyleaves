package com.example.plantmonitor.UserPlants;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantmonitor.PlantCatalog.EditPlantCatalogActivity;
import com.example.plantmonitor.PlantCatalog.MyAdapterCatalog;
import com.example.plantmonitor.PlantCatalog.Plant;
import com.example.plantmonitor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapterUserPlants extends RecyclerView.Adapter<MyAdapterUserPlants.MyViewHolder> {

    private String TAG = "MyAdapterUserPlants";

    private FirebaseDatabase database;
    private DatabaseReference databasePlants;

    ArrayList<String> userPlantKeyArray;
    ArrayList<OwnsA> ownsAArray;
    ArrayList<String> plantKeyArray = new ArrayList<String>();
    ArrayList<Plant> userPlantArray = new ArrayList<Plant>();
    Context context;

    public MyAdapterUserPlants(Context context, ArrayList<String> userPlantKeyArray, ArrayList<OwnsA> ownsAArray, ArrayList<String> plantKeyArray, ArrayList<Plant> userPlantArray){
        this.context = context;
        this.userPlantKeyArray = userPlantKeyArray;
        this.ownsAArray = ownsAArray;

        for (int i = 0; i < ownsAArray.size(); i++) {
            for (int j = 0; j < userPlantArray.size(); j++) {
                if (ownsAArray.get(i).getPlantID().equals(plantKeyArray.get(j))) {
                    this.plantKeyArray.add(plantKeyArray.get(j));
                    this.userPlantArray.add(userPlantArray.get(j));
                    break;
                }
            }
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_userplant, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String userPlantKey = userPlantKeyArray.get(position);
        OwnsA ownsA = ownsAArray.get(position);
        Plant plant = userPlantArray.get(position);
        if (plant != null) {
            holder.textViewUserPlantName.setText(ownsA.getName());
            holder.textViewUserPlantType.setText(plant.getPlantName());
            holder.itemView.setOnClickListener((view) -> {goToPlantProfileActivity(view, userPlantKey, ownsA, plant);});
        }
        Log.d(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return ownsAArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserPlantName;
        TextView textViewUserPlantType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserPlantName = itemView.findViewById(R.id.textViewUserPlantName);
            textViewUserPlantType = itemView.findViewById(R.id.textViewUserPlantType);
        }
    }

    void goToPlantProfileActivity(View view, String userPlantKey, OwnsA ownsA, Plant plant) {
        Intent intent = new Intent(context, PlantProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putString("userPlantID", userPlantKey);
        bundle.putString("deviceID", ownsA.getDeviceID());
        bundle.putString("userPlantName", ownsA.getName());
        bundle.putString("plantID", ownsA.getPlantID());
        bundle.putString("plantName", plant.getPlantName());
        bundle.putInt("plantIdealLight", plant.getPlantIdealLight());
        bundle.putInt("plantIdealMoisture", plant.getPlantIdealMoisture());
        bundle.putInt("plantIdealTemperature", plant.getPlantIdealTemperature());
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }
}

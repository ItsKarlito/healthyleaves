package com.example.plantmonitor.UserPlants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantmonitor.General.HomeActivity;
import com.example.plantmonitor.PlantCatalog.MyAdapterCatalog;
import com.example.plantmonitor.PlantCatalog.Plant;
import com.example.plantmonitor.PlantCatalog.PlantCatalogActivity;
import com.example.plantmonitor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserPlantsListActivity extends AppCompatActivity {

    private String TAG = "UserPlantsDatabaseActivity";

    TextView textViewUserPlantsActivityTitle;
    Button buttonAddUserPlant = null;

    RecyclerView recyclerView;
    MyAdapterUserPlants myAdapterUserPlants;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceOwnsA;
    private DatabaseReference databaseReferencePlants;
    ArrayList<String> userPlantKeyArray = new ArrayList<String>();
    ArrayList<OwnsA> ownsAArray = new ArrayList<OwnsA>();
    ArrayList<String> plantKeyArray = new ArrayList<String>();
    ArrayList<Plant> userPlantArray = new ArrayList<Plant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_plants_list);

        recyclerView = findViewById(R.id.recyclerView);

        textViewUserPlantsActivityTitle = (TextView) findViewById(R.id.textViewUserPlantsActivityTitle);
        textViewUserPlantsActivityTitle.setOnClickListener((view) -> {goToHomeActivity();});

        buttonAddUserPlant = (Button) findViewById(R.id.buttonAddUserPlant);
        buttonAddUserPlant.setOnClickListener((view) -> {goToPlantCatalogActivity();});

        populateRecyclerView();
    }

    void populateRecyclerView() {

        database = FirebaseDatabase.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReferenceOwnsA = database.getReference("OwnsA");
        databaseReferenceOwnsA.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    OwnsA ownsA = child.getValue(OwnsA.class);
                    String userPlantKey = child.getKey();
                    if (ownsA.getUserID().equals(userID)) {
                        userPlantKeyArray.add(userPlantKey);
                        ownsAArray.add(ownsA);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Failed to load OwnsA", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReferencePlants = database.getReference("Plants");
        databaseReferencePlants.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Plant plant = child.getValue(Plant.class);
                    String plantKey = child.getKey();
                    for (int i = 0; i < ownsAArray.size(); i++) {
                        if (ownsAArray.get(i).getPlantID().equals(plantKey)) {
                            plantKeyArray.add(plantKey);
                            userPlantArray.add(plant);
                        }
                    }
                }
                myAdapterUserPlants = new MyAdapterUserPlants(getApplicationContext(), userPlantKeyArray, ownsAArray, plantKeyArray, userPlantArray);
                recyclerView.setAdapter(myAdapterUserPlants);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Failed to load Plants", Toast.LENGTH_SHORT).show();
            }
        });

        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
    }

    void goToPlantCatalogActivity() {
        Intent intent = new Intent(this, PlantCatalogActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Choose your plant's type", Toast.LENGTH_SHORT).show();
    }

    void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
package com.example.plantmonitor.General;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plantmonitor.MainActivity;
import com.example.plantmonitor.PlantCatalog.PlantCatalogActivity;
import com.example.plantmonitor.R;
import com.example.plantmonitor.UserPlants.PlantTemperatureDetailsActivity;
import com.example.plantmonitor.UserPlants.UserPlantsListActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    TextView textViewUsername;
    Button buttonGoToUserPlantsListActivity;
    Button buttonGoToPlantCatalogActivity;
    Button buttonGoToPlantIdentifierApplication;
    Button logout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String userName = userEmail.split("@")[0];
        textViewUsername.setText(userName);
        buttonGoToUserPlantsListActivity = (Button) findViewById(R.id.buttonGoToUserPlantsListActivity);
        buttonGoToUserPlantsListActivity.setOnClickListener((view) -> {goToUserPlantsListActivity();});
        buttonGoToPlantCatalogActivity = (Button) findViewById(R.id.buttonGoToPlantCatalogActivity);
        buttonGoToPlantCatalogActivity.setOnClickListener((view) -> {goToPlantCatalogActivity();});
        buttonGoToPlantIdentifierApplication = (Button) findViewById(R.id.buttonGoToPlantIdentifierApplication);
        buttonGoToPlantIdentifierApplication.setOnClickListener((view) -> {goTobuttonGoToPlantIdentifierApplication();});
        logout = (Button) findViewById(R.id.home_logout_button);
        logout.setOnClickListener((view) -> {userLogout();});
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener((view) -> {goToUserSettingsActivity();});
    }

    void goToUserPlantsListActivity() {
        Intent intent = new Intent(this, UserPlantsListActivity.class);
        //Intent intent = new Intent(this, PlantTemperatureDetailsActivity.class);
        startActivity(intent);
    }

    void goToPlantCatalogActivity() {
        Intent intent = new Intent(this, PlantCatalogActivity.class);
        startActivity(intent);
    }

    void goToUserSettingsActivity() {
        Intent intent = new Intent(this, UserSettingsActivity.class);
        startActivity(intent);
    }

    void goTobuttonGoToPlantIdentifierApplication() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        PackageManager managerclock = getPackageManager();
        i = managerclock.getLaunchIntentForPackage("org.tensorflow.lite.examples.classification");
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(i);
        /*
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setClassName("org.tensorflow.lite.examples.classification", "com.xxx.your_class_name");
        startActivity(i);

         */
    }

    void userLogout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
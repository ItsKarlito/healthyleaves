package com.example.plantmonitor.Settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.example.plantmonitor.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
@RequiresApi(api = Build.VERSION_CODES.O)
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    /**
     * LowTemperature, HighTemperature, LowMoisture, HighMoisture, LowLight, HighLight
     */
    private int LT, HT, LM, HM, LL, HL;
    private String title, body;
    boolean shouldNotify = false;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        initTempRecommendations();
        System.out.println(remoteMessage.getData());

        if (remoteMessage.getData() != null) {

            /**
             * Parse data from cloud function
             */
            Map<String, String> values = remoteMessage.getData();

            /**
             * Change values to strings
             */
            String type = values.get("type");
            String value = values.get("value");
            String time = values.get("time");
            String userPlantId = values.get("userPlantId");

            /**
             * Debug purposes
             */
            System.out.println(values);
            System.out.println(value);
            System.out.println(time);
            System.out.println(userPlantId);

            /**
             * Needs to be integer to verify if lower or higher than recommendation
             */
            int intValue = Integer.parseInt(value);

            switch (type){
                case "temperature":
                    tempNotification(intValue);
                    break;
                case "light":
                    lightNotification(intValue);
                    break;
                case "moisture":
                    moistureNotification(intValue);
                    break;
            }

            /**
             * Display notification according to the received data
             */
            if(shouldNotify)
                NotificationHelper.displayNotification(getApplicationContext(), title, body);
            this.shouldNotify = false;
        }
    }

    /**
     * INITIALIZE PARAMETERS FOR TEMPERATURE NOTIFICATION
     * @param c
     * @param i
     */
    private void temperatureLowNotification(Context c, int i){
        this.title = c.getString(R.string.lowTempTitle);
        this.body = c.getString(R.string.lowTempBody) + i;
        this.shouldNotify = true;
    }

    private void temperatureHighNotification(Context context, int i){
        this.title = context.getString(R.string.highTempTitle);
        this.title = context.getString(R.string.highTempBody) + i;
        this.shouldNotify = true;
    }

    private void tempNotification(int val){
        if (val < this.LT)
            temperatureLowNotification(getApplicationContext(), val);
        else if(val > this.HT)
            temperatureHighNotification(getApplicationContext(), val);
    }

    /**
     * INITIALIZE PARAMETERS FOR LIGHT NOTIFICATION
     * @param c
     * @param i
     */
    private void lightLowNotification(Context c, int i){
        this.title = c.getString(R.string.lowLightTitle);
        this.body = c.getString(R.string.lowLightBody) + i;
        this.shouldNotify = true;
    }

    private void lightHighNotification(Context context, int i){
        this.title = context.getString(R.string.highLightTitle);
        this.title = context.getString(R.string.highLightBody) + i;
        this.shouldNotify = true;
    }

    private void lightNotification(int val){
        if (val < this.LL)
            lightLowNotification(getApplicationContext(), val);
        else if(val > this.HL)
            lightHighNotification(getApplicationContext(), val);
    }

    /**
     * INITIALIZE PARAMETERS FOR MOISTURE NOTIFICATION
     * @param c
     * @param i
     */
    private void moistureHighNotification(Context c, int i) {
        this.title = c.getString(R.string.highMoistureTitle);
        this.body = c.getString(R.string.highMoistureBody) + i;
        this.shouldNotify = true;
    }

    private void moistureLowNotification(Context c, int i) {
        this.title = c.getString(R.string.lowMoistureTitle);
        this.body = c.getString(R.string.lowMoistureBody) + i;
        this.shouldNotify = true;
    }

    private void moistureNotification(int val) {
        if (val < this.LM)
            moistureLowNotification(getApplicationContext(), val);
        else if(val > this.HM)
            moistureHighNotification(getApplicationContext(), val);
    }

    //TODO: THIS IS WHERE TEMPERATURES SHOULD BE CHANGED ACCORDING TO PLANT ID
    private void initTempRecommendations(){
        this.LT = 17; //change these values to fix when notifications should get triggered.
        this.HT = 28; //values 17 and 28 were used as defaults.
        this.LM = 50;
        this.HM = 95;
        this.LL = 40;
        this.HL = 90;
    }

}


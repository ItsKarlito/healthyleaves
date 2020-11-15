package com.example.plantmonitor.UserPlants;

public class Data {

    int time;
    int userPlantID;
    int value;

    Data(){
        time = 0;
        userPlantID = -1;
        value = 0;
    }

    Data(int t, int u, int v){
        time = t;
        userPlantID = u;
        value = v;
    }

    public int getTime() {
        return time;
    }

    public int getUserPlantID() {
        return userPlantID;
    }

    public int getValue() {
        return value;
    }
}

package com.example.plantmonitor.UserPlants;

public class Light {
    private int time;
    private String userPlantId;
    private int value;

    public Light() {
    }

    public Light(int time, String userPlantId, int value) {
        this.time = time;
        this.userPlantId = userPlantId;
        this.value = value;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getUserPlantId() {
        return userPlantId;
    }

    public void setUserPlantId(String userPlantId) {
        this.userPlantId = userPlantId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Light{" +
                "time=" + time +
                ", userPlantId='" + userPlantId + '\'' +
                ", value=" + value +
                '}';
    }
}

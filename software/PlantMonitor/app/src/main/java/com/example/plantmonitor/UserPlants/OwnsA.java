package com.example.plantmonitor.UserPlants;

public class OwnsA {
    private String name;
    private String plantID;
    private String userID;
    private String deviceID;

    public OwnsA() {
    }

    public OwnsA(String name, String plantID, String userID, String deviceID) {
        this.name = name;
        this.plantID = plantID;
        this.userID = userID;
        this.deviceID = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlantID() {
        return plantID;
    }

    public void setPlantID(String plantID) {
        this.plantID = plantID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() { return deviceID; }

    public void setDeviceID(String deviceID) {this.deviceID = deviceID; }

    @Override
    public String toString() {
        return "OwnsA{" +
                "name='" + name + '\'' +
                ", plantID='" + plantID + '\'' +
                ", userID='" + userID + '\'' +
                ", deviceID='" + deviceID + '\'' +
                '}';
    }
}

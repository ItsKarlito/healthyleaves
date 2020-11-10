package com.example.plantmonitor.UserPlants;

public class OwnsA {
    public String name = "";
    public String plantID = "";
    public String userID = "";

    public OwnsA() {
    }

    public OwnsA(String name, String plantID, String userID) {
        this.name = name;
        this.plantID = plantID;
        this.userID = userID;
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

    @Override
    public String toString() {
        return "OwnsA{" +
                "name='" + name + '\'' +
                ", plantID='" + plantID + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}

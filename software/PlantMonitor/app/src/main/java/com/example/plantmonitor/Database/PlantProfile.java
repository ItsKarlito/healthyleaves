package com.example.plantmonitor.Database;

import org.w3c.dom.NameList;

public class PlantProfile {
    private Integer Id;
    private String name;
    private String waterInterval;
    private String lightExposure;
    private String temperature;
    private String growth;

    public PlantProfile( Integer id, String Name, String interval, String exposure, String temp, String Growth) {
        Id = id;
        name = Name;
        waterInterval = interval;
        lightExposure = exposure;
        temperature = temp;
        growth = Growth;
    }

    public PlantProfile(String Name, String interval, String exposure, String temp, String Growth) {
        name = Name;
        waterInterval = interval;
        lightExposure = exposure;
        temperature = temp;
        growth = Growth;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWaterInterval() {
        return waterInterval;
    }

    public void setWaterInterval(String waterInterval) {
        this.waterInterval = waterInterval;
    }

    public String getLightExposure() {
        return lightExposure;
    }

    public void setLightExposure(String lightExposure) {
        this.lightExposure = lightExposure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }
}








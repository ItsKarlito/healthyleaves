package com.example.plantmonitor.PlantCatalog;

public class Plant {
    public String plantName = "";
    public int plantIdealLight = 0;
    public int plantIdealMoisture = 0;
    public int plantIdealTemperature = 0;
    public String plantDescription = "";

    public Plant() {
    }

    public Plant(String plantName, int plantIdealLight, int plantIdealMoisture, int plantIdealTemperature, String plantDescription) {
        this.plantName = plantName;
        this.plantIdealLight = plantIdealLight;
        this.plantIdealMoisture = plantIdealMoisture;
        this.plantIdealTemperature = plantIdealTemperature;
        this.plantDescription = plantDescription;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public int getPlantIdealLight() {
        return plantIdealLight;
    }

    public void setPlantIdealLight(int plantIdealLight) {
        this.plantIdealLight = plantIdealLight;
    }

    public int getPlantIdealMoisture() {
        return plantIdealMoisture;
    }

    public void setPlantIdealMoisture(int plantIdealMoisture) {
        this.plantIdealMoisture = plantIdealMoisture;
    }

    public int getPlantIdealTemperature() {
        return plantIdealTemperature;
    }

    public void setPlantIdealTemperature(int plantIdealTemperature) {
        this.plantIdealTemperature = plantIdealTemperature;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "plantName='" + plantName + '\'' +
                ", plantIdealLight=" + plantIdealLight +
                ", plantIdealMoisture=" + plantIdealMoisture +
                ", plantIdealTemperature=" + plantIdealTemperature +
                ", plantDescription='" + plantDescription + '\'' +
                '}';
    }
}

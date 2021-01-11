# ELEC/COEN 390 Engineering Team Design Project

## Contributors
Daniel Savin  
Jun Young David Kim  
Karl Noory  
Thomas Tran  
Vily Badea  

## Supervision
Bipin Patel  
Wahab Hamou Lhadj  

## Technology Stack
Android Studio IDE  
Arduino C/C++
Arduino IDE  
ESP32 Microcontroller  
Firebase  
Git  
Java  
KiCAD  
LDR Light Sensor  
SEN0193 Capcitive Moisture Sensor  
TMP36 Temperature Sensor  

## App Installation Guide
### Steps
1. Clone project repository.
1. Open PlantMonitor Android project in .software/PlantMonitor.
1. Build Gradle of PlantMonitor Android app.
1. Run PlantMonitor Android app.
1. Open PlantIdentier Android project in .software/PlantIdentifier.
1. Build Gradle of PlantIdentifier Android app.
1. Run PlantIdentifier Android app.
1. You can now access the application called "Healthy Leaves".

### Notes
Make sure the filepath for the SDK manager points to your own directory.  
The build.gradle file will need to specify the version of your own Android Studio version.  
A device is not requiered to run the app.  
To pair the device to our demo device on Karl's plant, enter the device code as "-MLuKU9iZdUl5zLGsuPz".  

## Microcontroller Setup Guide
### Steps
1. Clone project repository.
1. Download and install the [FirebaseESP32](https://www.arduino.cc/reference/en/libraries/firebase-esp32-client/) client library. Import the library into the [Arduino IDE](https://www.arduino.cc/en/software).
1. Open the HealthyLeaves Arduino project located at engineering-team-design-project/hardware/sparkfunESP32/HealthyLeaves/
1. Edit sketch with proper WiFi and Firebase credentials.
1. Attach the HealthyLeaves sensor shield to the SparkFun ESP32 Thing.
1. Connect the SparkFun ESP32 Thing microcontroller to your computer via USB port. Follow the steps [here](https://learn.sparkfun.com/tutorials/esp32-thing-hookup-guide) if the Arduino IDE does not recognise the SparkFun ESP32 Thing.
1. Compile and upload the sketch to the SparkFun ESP32 Thing.
1. Place the end of the moisture sensor located on the HealthyLeaves sensor shield into the plant you want to monitor.


### Notes
A device is not requiered to run the app.  
To pair the device to our demo device on Karl's plant, enter the device code as "-MLuKU9iZdUl5zLGsuPz" in the HealthyLeaves Arduino sketch.  

## Pictures and Screenshots
### Android Application
![](/software/screenshots/loginSignup.png)
![](/software/screenshots/home.png)
![](/software/screenshots/plantCatalog.png)
![](/software/screenshots/addPlantToCatalog.png)
![](/software/screenshots/editPlantToCatalog.png)
![](/software/screenshots/addPlantToUser.png)
![](/software/screenshots/livePlantSensorData.png)
![](/software/screenshots/lightGraph.png)
![](/software/screenshots/moistureGraph.png)
![](/software/screenshots/temperatureGraph.png)
![](/software/screenshots/notification.png)
![](/software/screenshots/userPlants.png)
![](/software/screenshots/rose.png)
![](/software/screenshots/credits.png)

### Hardware
![](/hardware/pictures/1.jpg)
![](/hardware/pictures/2.jpg)
![](/hardware/pictures/3.jpg)
![](/hardware/pictures/4.jpg)

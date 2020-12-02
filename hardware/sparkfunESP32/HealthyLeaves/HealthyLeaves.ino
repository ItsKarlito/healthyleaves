/*
HealthyLeaves - ELEC/COEN 390 Team Project
Last modified: 2020-11-14
Hardware code for the HealthyLeaves device.
*/

//libraries
#include <WiFi.h>
#include <time.h>
#include <FirebaseESP32.h>

//definitions
#define SYNC 5000

//Firebase authentication
#define FIREBASE_HOST "https://healthyleaves-a5549.firebaseio.com/"
#define FIREBASE_AUTH "BlLToB9zFk5KjOzP3HkGUeBjVcWOsTKe56QPySID"
FirebaseData firebaseData;//define FirebaseESP32 data object

//WiFi authentication
#define WIFI_SSID "BELL197"
#define WIFI_PASSWORD "6527757A"

//time configuration
const char* ntpServer = "pool.ntp.org";//time server
const long  gmtOffset_sec = 0;//correction for timezone
const int   daylightOffset_sec = 0;//daylight offset

//sensors available
enum Sensor {light, moisture, temperature};//list of sensors

//user data identification
int id = 0;
String userPlantId = "-MLuKU9iZdUl5zLGsuPz";

void setup()
{
  //initialize Serial Monitor
  Serial.begin(115200);

  //initialize WiFi
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(2000);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.print(WiFi.localIP());
  Serial.println();

  //initialize Firebase
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);
  Firebase.setReadTimeout(firebaseData, 1000 * 60);
  Firebase.setwriteSizeLimit(firebaseData, "tiny");

  // Configure System Time
  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer);
}

void loop()
{
  writeToDatabase(light, getLight());
  delay(SYNC);
  writeToDatabase(moisture, getMoisture());
  delay(SYNC);
  writeToDatabase(temperature, getTemperature());

  Serial.println("********************");
  Serial.println();
  id++;//create a new reading
  delay(SYNC);
}

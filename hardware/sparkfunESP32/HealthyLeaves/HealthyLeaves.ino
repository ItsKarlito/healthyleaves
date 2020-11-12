// HealthyLeaves - ELEC/COEN 390 Team Project

// Defined constants
#define DELAY 1000

// Libraries
#include <WiFi.h>
#include <time.h>
#include <FirebaseESP32.h>

// Firebase authentication
#define FIREBASE_HOST "https://healthyleaves-a5549.firebaseio.com/"
#define FIREBASE_AUTH "BlLToB9zFk5KjOzP3HkGUeBjVcWOsTKe56QPySID"

// Define FirebaseESP32 data object
FirebaseData firebaseData;

// WiFi authentication
#define WIFI_SSID "BELL197"
#define WIFI_PASSWORD "6527757A"

// Time configuration
const char* ntpServer = "pool.ntp.org";
const long  gmtOffset_sec = 0;
const int   daylightOffset_sec = 0;

// Sensors available
enum Sensor { light, moisture, temperature };

// Light sensor
int lightIntensity = 36;
int lightIntensityRaw = 0;
int lightIntensityRawMax = 0;
int lightIntensityPercent = 0;

// Temperature sensor
int tmp = 38;
int tmpRaw = 0;
float tmpCelsius = 0;

// User data identification
int id = 0;
String userPlantId = "-ML5WnnH09bhO1KyTsyR";

void setup()
{
  // Initialize Serial Monitor
  Serial.begin(115200);

  // Initialize WiFi
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(100);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();

  // Initialize Firebase
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);
  Firebase.setReadTimeout(firebaseData, 1000 * 60);
  Firebase.setwriteSizeLimit(firebaseData, "tiny");

  // Configure System Time
  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer);
}

void loop()
{
  writeToDatabase(light, getLightIntensity());
  writeToDatabase(temperature, getTemperature());

  Serial.println("********************");
  Serial.println();
  id++;
  delay(DELAY);
}

#include <WiFi.h>
#include <time.h>
#include <FirebaseESP32.h>

// Firebase auth
#define FIREBASE_HOST "https://healthyleaves-a5549.firebaseio.com/"
#define FIREBASE_AUTH "BlLToB9zFk5KjOzP3HkGUeBjVcWOsTKe56QPySID"

// Define FirebaseESP32 data object
FirebaseData firebaseData;

// WiFi auth
#define WIFI_SSID "BELL197"
#define WIFI_PASSWORD "6527757A"

// Time config
const char* ntpServer = "pool.ntp.org";
const long  gmtOffset_sec = 0;
const int   daylightOffset_sec = 0;

// Valid sensors
enum Sensor { light, moisture, temperature };

// User id
int id = 0;
int userPlantId = 123;

// Mock data
int mockData = 1;

// Convert system time to epoch time
int getEpochTime() {
  struct tm timeInfo;
  if (!getLocalTime(&timeInfo)) {
    return -1;
  }
  time_t timeSinceEpoch = mktime(&timeInfo);
  return timeSinceEpoch;
}

// Send sensor data to Firebase
void writeToDatabase(Sensor sensor, int value) {
  int timeStamp = getEpochTime();

  String sensorName;
  switch (sensor) {
    case light:
      sensorName = "Light";
      break;
    case moisture:
      sensorName = "Moisture";
      break;
    case temperature:
      sensorName = "Temperature";
      break;
    default:
      sensorName = "Unknown";
      break;
  };

  String pathUserPlantId = "/" + String(sensorName) + "/" + String(id) + "/userPlantId";
  String pathValue = "/" + String(sensorName) + "/" + String(id) + "/value";
  String pathTime = "/" + String(sensorName) + "/" + String(id) + "/time";


  if (Firebase.set(firebaseData, pathUserPlantId, userPlantId))
  {
    Serial.println("PASSED: " + String(userPlantId) + " was stored at " + pathUserPlantId);
  }
  else
  {
    Serial.println("FAILED: " + firebaseData.errorReason());
  }

  if (Firebase.set(firebaseData, pathValue, value))
  {
    Serial.println("PASSED: " + String(value) + " was stored at " + pathValue);
  }
  else
  {
    Serial.println("FAILED: " + firebaseData.errorReason());
  }

  if (Firebase.set(firebaseData, pathTime, timeStamp))
  {
    Serial.println("PASSED: " + String(timeStamp) + " was stored at " + pathTime);
    Serial.println("------------------------------------");
    Serial.println();
  }
  else
  {
    Serial.println("FAILED: " + firebaseData.errorReason());
    Serial.println("------------------------------------");
    Serial.println();
  }
}

void setup()
{
  Serial.begin(115200);

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

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);

  // Set database read timeout to 1 minute (max 15 minutes)
  Firebase.setReadTimeout(firebaseData, 1000 * 60);
  // tiny, small, medium, large and unlimited.
  // Size and its write timeout e.g. tiny (1s), small (10s), medium (30s) and large (60s).
  Firebase.setwriteSizeLimit(firebaseData, "tiny");

  // Set system time
  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer);

  for (int i = 0; i < 5; i++)
  {
    writeToDatabase(light, mockData * 2);
    writeToDatabase(moisture, mockData * 3);
    writeToDatabase(temperature, mockData * 4);
    mockData++;
    id++;
  }
}

void loop()
{

}

// writeToDatabase

void writeToDatabase(Sensor sensor, int value) 
{
  int timeStamp = getEpochTime();

  //string to store sensor name
  String sensorName;
  switch (sensor) 
  {
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

  //generate paths for plant ID, value of sensoor and time of reading
  String pathUserPlantId = "/" + String(sensorName) + "/" + String(id) + "/userPlantId";
  String pathValue = "/" + String(sensorName) + "/" + String(id) + "/value";
  String pathTime = "/" + String(sensorName) + "/" + String(id) + "/time";

  //push user plant ID to database and check if it worked
  if (Firebase.set(firebaseData, pathUserPlantId, userPlantId))
  {
    Serial.println("PASSED: " + String(userPlantId) + " was stored at " + pathUserPlantId);
  }
  else
  {
    Serial.println("FAILED: " + firebaseData.errorReason());
  }
  
  //push sensor value to database and check if it worked
  if (Firebase.set(firebaseData, pathValue, value))
  {
    Serial.println("PASSED: " + String(value) + " was stored at " + pathValue);
  }
  else
  {
    Serial.println("FAILED: " + firebaseData.errorReason());
  }

  //push time of reading to database and check if it worked
  if (Firebase.set(firebaseData, pathTime, timeStamp))
  {
    Serial.println("PASSED: " + String(timeStamp) + " was stored at " + pathTime);
    Serial.println();
  }
  else
  {
    Serial.println("FAILED: " + firebaseData.errorReason());
    Serial.println();
  }
}

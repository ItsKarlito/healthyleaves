//function returns moisture % from a capacitive sensor
//sensor: Capacitive Soil Moisture Sensor v2.0

int getMoisture()
{
  moistureRaw= analogRead(moisturePin);//read data
  //Serial.println(moisture);//debug raw data
  moistureRaw = constrain(moistureRaw, water, air);//constrain data so no overflow
  moisturePercent = map(moistureRaw, water, air, 100, 0);//map to %
  return moisturePercent;
}

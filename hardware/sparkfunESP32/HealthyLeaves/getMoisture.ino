//function returns moisture % from a capacitive sensor
//sensor: Capacitive Soil Moisture Sensor v2.0

//variables
int moisturePin= 37;//pin
int air = 3300;//value when exposed to air (0% moisture)
int water = 2800;//value when exposed to water (100% moisture)
int moistureRaw;//raw data
int moisturePercent;//processed data

int getMoisture()
{
  moistureRaw= analogRead(moisturePin);//read data
  Serial.println(moistureRaw);//debug raw data
  moistureRaw = constrain(moistureRaw, water, air);//constrain data so no overflow
  moisturePercent = map(moistureRaw, water, air, 100, 0);//map to %
  return moisturePercent;
}

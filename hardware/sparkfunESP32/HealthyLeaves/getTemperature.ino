//function returns temperature in Celcius
//sensor: TMP36

//temperature sensor
int tmpPin = 38;
int tmpRead;
float tmpRaw;//float for calculations

int getTemperature()
{
  // Convert analog reading to celsius and calibrate
  tmpRaw = analogRead(tmpPin);
  int tmpCelsius = (((tmpRaw * (3.3 / 1024)) - 0.5) * 10) + 6.8;
  return tmpCelsius;
}



//tmpCelsius = (((tmpRaw * (3.3 / 1024)) - 0.5) * 10) + 6.8;//prev eq

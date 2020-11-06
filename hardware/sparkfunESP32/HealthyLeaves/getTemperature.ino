// getTemperature

int getTemperature()
{
  // Convert analog reading to celsius and calibrate
  tmpRaw = analogRead(tmp);
  tmpCelsius = (((tmpRaw * (3.3 / 1024)) - 0.5) * 10) + 6.8;
  return tmpCelsius;
}

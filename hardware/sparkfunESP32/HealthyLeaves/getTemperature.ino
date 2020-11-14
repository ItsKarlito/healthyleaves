//function returns temperature in Celcius
//sensor: tmp36

int getTemperature()
{
  tmpRaw = analogRead(tmp);//read data
  tmpCelsius = (((tmpRaw * (3.3 / 1024)) - 0.5) * 10) + 6.8;//see spec sheet
  return tmpCelsius;
}

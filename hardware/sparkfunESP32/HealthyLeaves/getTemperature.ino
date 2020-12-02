//function returns temperature in Celcius
//sensor: TMP36

//temperature sensor
int tmpPin = 38;
int tmpRead;
float tmpRaw;//float for calculations

int getTemperature()
{
  tmpRead = analogRead(tmpPin);//read data
  tmpRaw = float(tmpRead);
  tmpRaw = (((tmpRaw / 4096) * 3.3 * 1000) - 500) / 100;
  return int(tmpRaw + 0.5);//rounding trick float->int
}



//tmpCelsius = (((tmpRaw * (3.3 / 1024)) - 0.5) * 10) + 6.8;//prev eq

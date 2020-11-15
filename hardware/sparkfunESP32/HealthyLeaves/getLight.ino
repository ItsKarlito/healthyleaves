//function returns the intensity of light in %
//light sensor: generic photoresistor (?k ohm)

//variables
int lightPin = 36;//pin
int lightRaw = 0;//raw data
int lightRawMax = 0;//value when exposed to direct sunlight (100% light)
int lightPercent = 0;//processed data

int getLight()
{
  lightRaw = analogRead(lightPin);//get raw data
  lightRaw = constrain(lightRaw, 0, 4095);//constrain data so no overflow
  lightPercent = map(lightRaw, 0, 4095, 0, 100);//map to %

  /*
     //debug: capture max value for calibration
     if (lightRaw > lightRawMax)
     {
       lightRawMax = lightRaw;
       Serial.println(lightRawMax);
     }
  */

  return lightPercent;
}

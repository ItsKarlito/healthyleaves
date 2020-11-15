//function returns the intensity of light in %
//light sensor: generic photoresistor (?k ohm)

int getLight()
{
  lightRaw = analogRead(lightPin);//get raw data
  lightRaw = constrain(lightRaw, 0, 4095);//constrain data so no overflow
  lightPercent = map(lightRaw, 0, 4096, 0, 100);//map to %

  /*
     //debug: capture max value for calibration
     if (lightRawRaw > lightRawMax)
     {
       lightRawMax = lightRaw;
       Serial.println(lightRawMax);
     }
  */

  return lightPercent;
}

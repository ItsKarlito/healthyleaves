// getLightIntensity

int getLightIntensity()
{
  lightIntensityRaw = analogRead(lightIntensity);
  lightIntensityRaw = constrain(lightIntensityRaw, 0.0, 4096.0);
  lightIntensityPercent = map(lightIntensityRaw, 0.0, 4096.0, 0.0, 100.0);

  /*
     // Capture max value for lightIntensityRaw variable. Use this calibrate
     if (lightIntensityRaw > lightIntensityRawMax)
     {
       lightIntensityRawMax = lightIntensityRaw;
       Serial.println(lightIntensityRawMax);
     }
  */

  return lightIntensityPercent;
}

//Function that get the data from a capacitive moisture sensor.
//Return an integer, and prints information to screen.

//Value when exposed to air (0% moisture) + 5
int air = 600;//+5 to extend range
//Value when exposed to water (100% moisture) - 5
int water = 245;//-5 to extend range
//Not that the moisture is not linear

//variables
int moisture;
int moistureRef = (air-water)/3;
int sensorPin = A0;//choose your analog pin here

int getMoisture()
{
  moisture = analogRead(sensorPin);//read data from analog pin 0
  //Serial.println(moisture);//debug raw data
  if(moisture >= water && moisture < water + moistureRef)
  {Serial.println("Very wet");}
  else if(moisture >= water + moistureRef && moisture < air - moistureRef)
  {Serial.println("Wet");}
  else if(moisture >= air - moistureRef && moisture <= air)
  {Serial.println("Dry");}
  else
  {Serial.println("Out of range");}

  delay(1000);
  return moisture;
}

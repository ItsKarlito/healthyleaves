//Function that get the data from a capacitive moisture sensor.
//Return an integer, and prints information to screen.

//Value when exposed to air (0% moisture)
int air = 595;
//Value when exposed to water (100% moisture)
int water = 250;
//Not that the moisture is not linear

//variables
int moisture;
int lim_moisture;
int sensorPin = A0;//choose your analog pin here

int getMoisture()
{
  moisture = analogRead(sensorPin);//read data from analog pin 0
  //Serial.println(moisture);//debug raw data
  lim_moisture = constrain(moisture, water, air);
  lim_moisture = map(lim_moisture, water, air, 100, 0);
  return lim_moisture;
}

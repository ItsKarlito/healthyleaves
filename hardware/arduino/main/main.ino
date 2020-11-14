/****************************************************/
//Name: Sensor Read
//Author: dansavin11@gmail.com
//Last modified: 2020-11-10
//Description: Program reads moisture data from a
//             capacitive sensor and translates it
//             to sensible readings.
/****************************************************/

//variables declaration
int moistureData = 0;//A0

void setup()
{
  Serial.begin(9600);//start serial comm with pc
}

void loop()
{
  moistureData = getMoisture();
  Serial.println(moistureData);
  delay(100);
}

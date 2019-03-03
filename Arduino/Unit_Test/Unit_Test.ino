
unsigned long previousMillis = 0;                 // Non blocking delay for sending MQTT message
float rzero, co2_ppm;                             // MQ135 readings
float temp, humi;                                 // DHT11 readings
String gpsLatitude, gpsLongitude, gpsLocation;    // Neo-6M readings




//void getDataAndPublish_delay()  {
//  unsigned long currentMillis = millis();
// 
//  if(currentMillis - previousMillis >= interval) {
//    // save the last time you read the sensor 
//    previousMillis = currentMillis;   
//
//    // Get CO2 from atmosphere  
//    rzero = gasSensor.getRZero();       //this to get the rzero value
//    co2_ppm = gasSensor.getPPM();           // this to get ppm value
//    // Now we can publish CO2 data!
//    Serial.print(F("\nSending CO2 Senosr PPM Value : "));
//    Serial.print("team-bear/co2 "  "1;" + String(co2_ppm));  
//    mclient.publish( "team-bear/co2 " ,  "1;" + String(co2_ppm));

void setup() {

  Serial.begin(115200);
  delay(100);
  Serial.println();
}
    void loop() {

     // Get Temperature and Humidity data from DHT11
    float humi = Serial.println("8 gram");        // Humidity
    float temp = Serial.println("12*c");     // Temperature (in Celsius)
    // Check if any reads failed and exit early (to try again).
    if (isnan(humi) || isnan(temp)) {
      Serial.println("\nFailed to read from DHT sensor!");
      return;
    }
    Serial.print(humi);
    Serial.print(temp); 
    
    
  }
//}

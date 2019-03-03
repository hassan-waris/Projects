"Copyright [2018] <Team Bear>"

#include <MQTT.h>
#include <PubSubClient.h>
#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>
#include <TinyGPS++.h>
#include <MQ135.h>
#include <DHT.h>
#include <Arduino.h>
#include <Wire.h>


unsigned int previousMillis = 0;    // Non blocking delay for MQTT message
float rzero, co2_ppm;                // MQ135 readings
float temp, humi;                    // DHT11 readings
String gpsLatitude, gpsLongitude, gpsLocation;  // Neo-6M readings

/****************Software serial for Neo-6m GPS mosule ***********************/
#define SOFT_RX 12
#define SOFT_TX 13

/*************************  DHT Sensor Pin   *********************************/
#define DHTPIN 14

/************************* Pin selection MQ135 *******************************/
#define ANALOGPIN A0

/******************  DHT Library Internal Parameter **************************/
#define DHTTYPE DHT11

/************************* WiFi Access Point *********************************/
const char *ssid =  "jbr";
const char *pass =  "66779762";

/*************************** cloudmqtt Setup *********************************/
const char *mqtt_server = "broker.hivemq.com";
const int mqtt_port = 1883;
const char *mqtt_client_name = "Client";  // Cant have same name as Client
/************************* Sensor Feed Setup *********************************/
const int interval = 1000;
#define CO2_SENSOR_FEED "team-bear/co2"
#define GPS_RECEIVER_FEED "team-bear/registerSensor"
#define DHT_HUMI_FEED "team-bear/humidity"
#define DHT_TEMP_FEED "team-bear/temperature"

// Create software serial class to connect Neo-6m GPS mosule
SoftwareSerial gpsSoftSerial(SOFT_RX, SOFT_TX);

// Create TinyGPS class to handle Neo-6m GPS mosule data
TinyGPSPlus gps;

// Create an ESP8266 WiFiClient class to connect to the MQTT server.
WiFiClient wclient;

//  Create an MQTT library class to connect to the MQTT server.
PubSubClient mclient(wclient, mqtt_server, mqtt_port);

//  Create an MQ135 class to connect CO2
MQ135 gasSensor = MQ135(ANALOGPIN);

//  Create an DHT class to connect sensor
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  //  Start serial communication with USB
  Serial.begin(115200);
  delay(100);
  Serial.println();

  //  Start communication with GPS module
  gpsSoftSerial.begin(9600);

  //  Start communication with DHT module
  dht.begin();
}

void loop() {
//  Connect to WiFi network
connectWiFi();
//  Connect ro MQTT Broker
connectMQTTbroker();
//  Read GPS Serial Data
readGPSData();
//  Check GPS Module Connection
checkGPSModule();
//  Get data form sensors and publish to MQTT broker
getDataAndPublish_delay();
}

void connectMQTTbroker() {
// Start by connecting to a MQTT Broker (Non Blocking Agent)
if (WiFi.status() == WL_CONNECTED) {
    if (!mclient.connected()) {
      Serial.println();
      Serial.println("Connecting to MQTT server");
      if (mclient.connect("mqtt_client_name")) {
        Serial.println("Connected to MQTT server");
      } else {
        Serial.println("Could not connect to MQTT server");
      }
    }
    // MQTT client connection to the server
if (mclient.connected()) {
      mclient.loop();
    }
  }
}
void connectWiFi() {
  // Start by connecting to the WiFi network
  if (WiFi.status() != WL_CONNECTED) {
    Serial.println();
    Serial.print("Connecting to WiFi ");
    Serial.print(ssid);
    Serial.println("...");
    WiFi.begin(ssid, pass);
    while (WiFi.status() != WL_CONNECTED) {
      delay(100);
    }
    Serial.print("Connected to WiFi with IP ");
    Serial.println(WiFi.localIP());
  }
}

//  Non-Blocking delay
void getDataAndPublish_delay()  {
  unsigned int currentMillis = millis();

  if (currentMillis - previousMillis >= interval) {
    // save the last time you read the sensor
    previousMillis = currentMillis;

        //  Get GPS data from Neo-M6 module
    gpsLatitude = String(gps.location.lat(), 6);      // Latitude
    gpsLongitude = String(gps.location.lng(), 6);     // Longitude
    gpsLocation = gpsLatitude + "," + gpsLongitude;   //  Location
    Serial.print(F("\nSending GPS Receiver Location Value : "));
    Serial.print("team-bear/registerSensor " "1;" + String(gpsLocation));
    mclient.publish("team-bear/registerSensor" , "1;" + String(gpsLocation));

    //  Get CO2 from atmosphere
    rzero = gasSensor.getRZero();      // this to get the rzero value
    co2_ppm = gasSensor.getPPM();      // this to get ppm value
    //  Now we can publish CO2 data!
    Serial.print(F("\nSending CO2 Senosr PPM Value : "));
    Serial.print("team-bear/co2 "  "1;" + String(co2_ppm));
    mclient.publish("team-bear/co2 " ,  "1;" + String(co2_ppm));

//  Get Temperature and Humidity data from DHT11
    humi = dht.readHumidity();        // Humidity
    temp = dht.readTemperature();     // Temperature (in Celsius)
    //  Check if any reads failed and exit early (to try again).
    if (isnan(humi) || isnan(temp)) {
    Serial.println("\nFailed to read from DHT sensor!");
      return;
    }
    Serial.print(F("\nSending DHT11 Humidity(%) : "));
    Serial.print(humi);
    mclient.publish("team-bear/humidity " , "1;"+ String(humi));
    Serial.print(F("\nSending DHT11 Temperature('C) : "));
    Serial.print(temp);
    mclient.publish("team-bear/temperature" , "1;"+ String(temp));
  }
}

void checkGPSModule() {
  if (millis() > 5000 && gps.charsProcessed() < 10) {
    Serial.println("No GPS data received: check wiring");
  }
}

void readGPSData() {
  while (gpsSoftSerial.available()) {
    gps.encode(gpsSoftSerial.read());
  }
}



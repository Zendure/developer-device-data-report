# zendure-developer-device-report

## About
Subscribe to device data for Zendure products. To receive information from the device, Developers can obtain the same device information as the official App by subscribing to Zendure MQTT Broker

Purpose：Open device data for all zendure App users, developers can obtain device information through the following steps

## The project provides:
  1、Apply to become a developer Api call example.  
  
  2、Create Mqtt client and device message subscription example. 
  
  3、Mqtt Broker endpoint configuration information.  

## Steps for Subscribe to device data

### Apply to be a developer
  1、First you need to register an account in Zendure App.  
  
  iOS: https://apps.apple.com/us/app/zendure/id1592645038  
  
  Android: https://play.google.com/store/apps/details?id=com.zendure.iot&pli=1  
  
  Web: https://app.zendure.com/download/  
  
  
  2、Familiar with MQTT protocol and client message subscription.  
  
  MQTT Endpoint: tcp://mqtt.zen-iot.com:1883
  
  3、To call the Rest Api to get the client configuration, you need to provide the device SN and Zendure App account.(Connect your device to WiFi and use the official mobile app to register a Zendure account and bind your device)  
  
  Endpoint: https://app.zendure.tech/v2/developer/api/apply  
  
  Request Method: POST  
  
  Required parameterized:  
    snNumber - the device serial number  
    account - Zendure App account username  
    
```java  
{  
  "snNumber":"VU5D99F74021B04",  
  "account":"dev@zendure.com"
}

```
Api Response:
```java
{  
  "code":200,  
  "success":true,  
  "data":{  
    "appKey":"zendure",  
    "secret":"zendureSecret",  
    "mqttUrl":"mqtt.zen-iot.com",  
    "port":1883  
   },
   "msg":"Successful operation"  
}  
```

  
### Subscribe to device news
  1、Create Mqtt client with appKey and appSecret.  
  2、Use your own device SN to subscribe to the topic /device/property/SN.  
  3、All possible metric names and their values sent by the device to the MQTT Zendure Broker. Data reporting via JSON object key/value. For example: device power, remaining discharge time.  
  ```java
  {  
    "electricLevel": 99,  
    "remainOutTime": 9999,  
    "socSet": 80  
  }  
  ```
 
 ### Device report data list
  
```java
electricLevel
remainOutTime
remainInputTime
socSet
batterCapacity
acInputLimit
slowChargePower
inputPower
acSwitch
acInputMode
acInputPower
acHz
acInputVoltage
acOutputMode
acOutputPower
acOutputVoltage
acOutputFactor
dcSwitch
dcInputMode
dcInputPower
outputPower
dcOutputPower
circleOutputPower
usb1OutputPower
usb2OutputPower
typec1Power
typec2Power
typec3Power
typec4Power
andersonPower
ambientSwitch
ambientLightMode
ambientLightColor
ambientLightNess
buzzerSwitch
masterSwitch
childLock
assistSwitch
assistGear
assistAngle
lampSwitch
lampMode
upsMode
socSet
machineStandTime
screenStandTime
wifiSwitch
wifiSignalLevel
blueState
wifiState
silentInput
ampUp
dcHardwareVersion
acHardwareVersion
bmsHardwareVersion
masterHardwareVersion
typecHardwareVersion
electricFanState
batteryNum
temperature
solarWorkMode
solarWorkOutputVoltage
solarOutputPower
assistDoubleFlash
seriesMode
parallelMode
```

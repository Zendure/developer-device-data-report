# Zendure Operating System v1.0.0

This document serves as a guide for developers who want to subscribe to device uplink data for Zendure products. By subscribing to the Zendure MQTT Broker, developers can access device information and receive data from the device, similar to the official app.

<p align="center">
  <img src="https://cdn.shopify.com/s/files/1/0276/0192/8266/files/520_310x.png?v=1677579037" alt="Logo">
</p>

## Overview

This report provides a guide for developers to subscribe to device uplink data for Zendure products. By subscribing to the Zendure MQTT Broker, developers can obtain device information and receive data from the device, similar to the official app.

## Purpose

The purpose of this project is to offer access to device data for all Zendure app users. Developers can follow the steps outlined in the report to obtain device information.

## Features

1. API call example for applying to become a developer.
2. Example of creating an MQTT client and device message subscription.
3. Configuration information for the MQTT Broker endpoint.

## Steps to Subscribe to Device Data

### Apply to be a developer

1. Register an account in the Zendure App.
    * iOS: [https://apps.apple.com/us/app/zendure/id1592645038](https://apps.apple.com/us/app/zendure/id1592645038)
    * Android: [https://play.google.com/store/apps/details?id=com.zendure.iot&pli=1](https://play.google.com/store/apps/details?id=com.zendure.iot&pli=1)
    * Web: [https://app.zendure.com/download/](https://app.zendure.com/download/)
2. Familiarize yourself with the MQTT protocol and client message subscription.
    * MQTT Endpoint: `tcp://mqtt.zen-iot.com:1883`
3. Call the REST API to obtain the client configuration by providing the device serial number (SN) and Zendure App account. (Connect your device to WiFi and use the official mobile app to register a Zendure account and bind your device. If multiple devices are bound, randomly select the SN of one of them.)
    * Endpoint: `https://app.zendure.tech/v2/developer/api/apply`
    * Request Method: POST
    * Required parameters:
        * snNumber - the device serial number
        * account - Zendure App account username

Example request body:

```json
{  
  "snNumber": "VU5D99F74021B04",  
  "account": "dev@zendure.com"
}
```
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
### Integrated Home Assistant

![image text](https://github.com/Zendure/developer-device-data-report/master/home Assistant.png

1. Select devices and services in Home Assistant, and add MQTT integration services. Use `appKey` as the username and `appSecret` as the password to connect to the MQTT broker.  
2. Replace the integrated Mqtt Discovery prefix with your appKey.  
3. For more information about the MQTT protocol, please refer to [https://mqtt.org/](https://mqtt.org/).
4. For more information on Home Assistant, please visit [https://www.home-assistant.io/](https://www.home-assistant.io/).
5. All possible metric names and their values sent by the device to the MQTT Zendure Broker are reported via JSON object key/value pairs. For example: device power, remaining discharge time.

```json
{
  "electricLevel": 99,
  "remainOutTime": 9999,
  "socSet": 80
}
```
### Device report data list
 
 | Field | Description | device_class |
| --- | --- | --- |
| electricLevel | Device battery percentage | sensor |
| remainOutTime | Remaining discharge time | sensor |
| remainInputTime | Remaining charging time | sensor |
| socSet | Charge Capacity Limitation | sensor |
| batterCapacity | battery capacity | sensor |
| acInputLimit | AC input limit | sensor |
| slowChargePower | Slow charging power | sensor |
| inputPower | total input power | sensor |
| acSwitch | AC switch | switch |
| acInputMode | AC input mode(1: Power grid 2: Charging cable) | sensor |
| acInputPower | AC input power | sensor |
| acHz | AC input frequency | sensor |
| acInputVoltage | ac input voltage | sensor |
| acOutputMode | AC output mode（1:UPS 2:120V 3:120V和240V） | sensor |
| acOutputPower | AC output power | sensor |
| acOutputVoltage | AC output voltage | sensor |
| acOutputFactor | AC output load factor | sensor |
| dcSwitch | DC switch | switch |
| dcInputMode | DC input mode(1: car charger 2: solar energy) | sensor |
| dcInputPower | DC input power | sensor |
| outputPower | total output power | sensor |
| dcOutputPower | DC output power | sensor |
| circleOutputPower | circle output power | sensor |
| usb1OutputPower | USB1 output power | sensor |
| usb2OutputPower | USB2 output power | sensor |
| typec1Power | TypeC1 output power | sensor |
| typec2Power | TypeC2 output power | sensor |
| typec3Power | TypeC3 output power | sensor |
| typec4Power | TypeC4 output power | sensor |
| andersonPower | Anderson output power | sensor |
| ambientSwitch | Ambient light switch | switch |
| ambientLightMode | Ambient light mode | sensor |
| ambientLightColor | Ambient light color | sensor |
| ambientLightNess | Ambient light brightness | sensor |
| buzzerSwitch | buzzer switch | switch |
| masterSwitch | master switch | switch |
| childLock | child lock switch | switch |
| assistSwitch | power wheel switch | switch |
| assistAngle | Power wheel angle | sensor |
| lampSwitch | light switch | switch |
| lampMode | light mode | sensor |
| upsMode | UPS mode | sensor |
| machineStandTime | automatic shutdown time | sensor |
| screenStandTime | automatic screen off time | sensor |
| wifiSwitch | wifi switch | switch |
| wifiSignalLevel | wifi signal level | sensor |
| blueState | blue state | sensor |
| wifiState | wifi state | sensor |
| silentInput | Silent charging mode (sleep mode) | sensor |
| ampUp | constant power mode | sensor |
| dcHardwareVersion | DC Hardware version | sensor |
| acHardwareVersion | AC hardware version | sensor |
| bmsHardwareVersion | BMS hardware version | sensor |
| masterHardwareVersion | MASTER hardware version | sensor |
| typecHardwareVersion | TYPEC hardware version | sensor |
| electricFanState | fan status | sensor |
| batteryNum | battery num | sensor |
| temperature | device temperature | sensor |
| solarWorkMode | Solar working mode | sensor |
| solarWorkOutputVoltage | Solar output voltage | sensor |
| solarOutputPower | Solar output power | sensor |
| assistDoubleFlash | Power wheel double flash switch | switch |
| seriesMode | series mode | sensor |
| parallelMode | parallel mode | sensor |

## Future Plans
1. Support data downlink and device control.
2. Support device LAN communication.
3. Integration of other Home Assistant plugins.

## Contact Us
If you have any questions or feedback, please feel free to contact us via the following methods. We welcome your feedback and suggestions and look forward to working with you:
* Email: [dev@zendure.com](mailto:dev@zendure.com)

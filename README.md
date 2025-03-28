# Zendure Operating System v1.0.0

This document serves as a guide for developers who want to subscribe to device uplink data for Zendure products. By subscribing to the Zendure MQTT Broker, developers can access device information and receive data from the device, similar to the official app.

<p align="center">
  <!-- <img src="https://cdn.shopify.com/s/files/1/0276/0192/8266/files/520_310x.png?v=1677579037" alt="Logo"> -->
  <img src="https://zendure.com/cdn/shop/files/zendure-logo-infinity-charge_240x.png?v=1717728038" alt="Logo">
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
    * MQTT Endpoint: Obtain the MQTT connection address and port number from the API results after applying to become a developer.

3. Call the REST API to obtain the client configuration by providing the device serial number (SN) and Zendure App account. (Connect your device to WiFi and use the official mobile app to register a Zendure account and bind your device. If multiple devices are bound, randomly select the SN of one of them.)
    * Endpoint(Golbal): `https://app.zendure.tech/v2/developer/api/apply`
    * Endpoint(Europe): `https://app.zendure.tech/eu/developer/api/apply`
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

## Supported products
Currently supports devices connected to WIFI  
- SuperBase V
  - 6400
  - 4600
- Satellite Battery
  - 6400
  - 4600
- SolarFlow
### Integrated Home Assistant

![image](https://zendure-oss.oss-cn-shenzhen.aliyuncs.com/app/github/homeassistant.png)

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

 #### SuperBase V
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

#### SolarFlow
 | Field | Description | device_class |
| --- | --- | --- |
| electricLevel | Device battery percentage | sensor |
| remainOutTime | Remaining discharge time | sensor |
| remainInputTime | Remaining charging time | sensor |
| socSet | Charge Capacity Limitation | sensor |
| outputLimit | output limit | sensor |
| solarInputPower | solar input power | sensor |
| packInputPower | pack input power | sensor |
| outputPackPower | output to pack power | sensor |
| outputHomePower | output to home power | sensor |
| packNum | pack num | sensor |
| packState | pack state(0:standby 1:input 2:output) | sensor |
| buzzerSwitch | buzzer switch | switch |
| masterSwitch | master switch | switch |
| solarPower1 | Solar1 Input Power | sensor | 
| solarPower2 | Solar2 Input Power | sensor |
| passMode | Bypass Mode 0：auto 1:always off 2:always on | sensor | 
| autoRecover | Automatic recovery of bypass mode settings 0:off 1:on | sensor | 
| packData | pack Data | sensor | 
| maxVol | The highest voltage among all cells, calculated as 'value * 0.01', unit: V | sensor | 
| minVol | The lowest voltage among all cells, calculated as 'value * 0.01', unit: V | sensor | 
| totalVol | The total voltage, which is the sum of all cells' voltages, calculated as 'value * 0.01', unit: V | sensor | 
| maxTemp | The maximum temperature, which represents the highest temperature value among all battery cells at the current (real-time) moment, unit: Kelvi| sensor | 
| socLevel | Current battery capacity of the battery pack | sensor | 
| sn |Current battery pack battery code | sensor | 
| hubState | Hub output status(0: stop output standby 1: stop output and shut down) | sensor |
#### The Fields Extended By  Hyper 2000 & Ace 1500 Based On SolarFlow
 | Field | Description | device_class |
| --- | --- | --- |
| gridInputPower | grid input power | sensor |
| acOutputPower | ac output power(Hyper 2000) | sensor |
| dcOutputPower | dc output power(Ace 1500) | sensor |
| acSwitch | ac switch | switch |
| dcSwitch | dc switch(Ace 1500) | switch |


## Future Plans
1. Support data downlink and device control.
2. Support device LAN communication.
3. Integration of other Home Assistant plugins.

## Contact Us
If you have any questions or feedback, please feel free to contact us via the following methods. We welcome your feedback and suggestions and look forward to working with you:
* Email: [dev@zendure.com](mailto:dev@zendure.com)

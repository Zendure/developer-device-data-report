package org.zendure.examples.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import java.util.UUID;


public class SubscribeSample {

    public static void main(String[] args)  {
        // mqttUrl and port assigned by the platform
        String host = "tcp://120.77.15.185:1883";
        String topic = "/device/property/VU5D99F74021B04";
        String clientId = UUID.randomUUID().toString();
        // appKey assigned by the platform
        String userName = "";
        // secret assigned by the platform
        String passWord = "";
        try {
            MqttClient client = new MqttClient(host, clientId);
            // Connection settings for MQTT
            MqttConnectOptions options = new MqttConnectOptions();
            // Set whether to clear the session. If it is set to false, it means that the server will keep the connection record of the client. If it is set to true, it means that it will connect with a new identity every time it connects to the server.
            options.setCleanSession(true);
            // Set the username for the connection
            options.setUserName(userName);
            // Set the password for the connection
            options.setPassword(passWord.toCharArray());
            // Set the timeout time in seconds
            options.setConnectionTimeout(30);
            // Set the session heartbeat time in seconds The server will send a message to the client every 1.5*20 seconds to determine whether the client is online, but this method does not have a reconnection mechanism
            options.setKeepAliveInterval(20);
            // Set callback function
            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost");
                }
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("topic:"+topic);
                    System.out.println("Qos:"+message.getQos());
                    System.out.println("message content:"+new String(message.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------"+ token.isComplete());
                }
            });
            client.connect(options);
            // subscribe news
            client.subscribe(topic);
            System.out.println("Mqtt client created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.sql.Time;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class Comminicuator  implements MqttCallbackExtended {
	
	
	private String brokerUrl="tcp://test.mosquitto.org:1883";
	private String clientID="Eclipse:)";
	protected String userName="aDahdal";
	private String password="12345";
	 private MqttClient mqttClient;
	 String data;
	 String messageInFile;
	 private static Comminicuator instance;
	 
	 //add list of listeners
	 private ArrayList<DataObservable> listeners;
	 
	 public static Comminicuator getInstance() {
		 if(instance==null) {
			 instance=new Comminicuator();
		 }
		 return instance;
	 }
	 
	 
	 public void addListener(DataObservable listener) {
		 if(listeners==null) {
			 listeners=new ArrayList<DataObservable>();
		 }
		 listeners.add(listener);


		 }
		 
	 
	public void removeListener(DataObservable listener){
		if(listeners==null) {
			return ;
		}
		else {
			listeners.remove(listener);
		}
	}
private  Comminicuator() {
	 super();

	 
 }
 
 public void start() {
	 try {
	 connect();
	 mqttClient.setCallback(this);

 }
	 catch(Exception e) {
		 e.printStackTrace();
	 }
 } 
 
 
 public void stopConnections() {
	 try {
		 disconnect();
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
 }
 private void connect() throws MqttException{ 
		 MemoryPersistence persistence=new MemoryPersistence();
		mqttClient=new MqttClient(brokerUrl, clientID,persistence);
		mqttClient.connect();
	 
 }
 
 
 public List<EnvData> parseData(String Data){
	 //the list to store the sensor's data
	 List<EnvData>list=new ArrayList<EnvData>();
	 //converting the String to Json array object
	 try {
		 JSONArray dataJsonArr=JSONArray.fromObject(Data);
		 for(int i=0;i<dataJsonArr.size();i++) {

			 //each item in the array is a JsonObject
			 JSONObject ScanJson=dataJsonArr.getJSONObject(i);
			 String deviceType=ScanJson.getString("type");
			 String mac=ScanJson.getString("mac");
			 String TS=ScanJson.getString("timestamp");
			 EnvData env=new EnvData(mac, TS);
			 list.add(env);
			 
			 if(deviceType.equals("S1")) {
				 double temp=ScanJson.getDouble("temperature");
				 double humidty=ScanJson.getDouble("humidity");
				 HTData htdata=new HTData(mac,TS,temp,humidty);
				 list.add(htdata);
			 }
			 if(deviceType.equals("iBeacon")) {
				 int rssi=ScanJson.getInt("rssi");
				 Ibeacon beacon=new Ibeacon(mac,TS,rssi);
				 list.add(beacon);
			 }

			 
			 }
		 
	 }
	 catch(Throwable e) {
		 e.printStackTrace();
	 }
	 //end of parsing the data "The functinon parseData"
	 return list;

 }
 
 
 
 
 
 private MqttConnectOptions getMqttConnectionOption() {
	 MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
	 mqttConnectOptions.setAutomaticReconnect(true);
	 mqttConnectOptions.setUserName(userName);
	 mqttConnectOptions.setPassword(password.toCharArray());
	 mqttConnectOptions.setMqttVersion(mqttConnectOptions.MQTT_VERSION_3_1_1);
	 return mqttConnectOptions;
 }

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void connectComplete(boolean arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void messageArrived(String topic, MqttMessage msg) throws Exception {
		
		// TODO Auto-generated method stub
		data=new String(msg.getPayload());
		List<EnvData> lst=parseData(data);
		notifyListeners(lst);

		
	}
	
	private void notifyListeners(List<EnvData> list) {

		if(listeners!=null) {
			for(DataObservable listener:listeners) {
				listener.onDataReceived(list);

			}
		}
	
}

public void subscribe(String topic,int qos) throws MqttException{
	mqttClient.subscribe(topic,qos);
}

public void unsubscribe(String topic) throws MqttException{
	mqttClient.unsubscribe(topic);
}

private void disconnect() throws MqttException{
	
	mqttClient.disconnect();
}

public void publish(String topic,MqttMessage msg) throws MqttException{
	mqttClient.publish(topic,msg);
}


	
}

package com.websocket.example.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

import com.websocket.example.model.Device;

public class DeviceSessionHandler {
		
	private int deviceId = 0;
	private Set<Device> deviceSet = new HashSet<Device>();
	private Set<Session> sessionSet = new HashSet<Session>();
	
	private static DeviceSessionHandler instance = null;
	
	protected DeviceSessionHandler() {
	}
	
	public static DeviceSessionHandler getInstance() {
      if(instance == null) {
         instance = new DeviceSessionHandler();
      }
      return instance;
	}

	public void addSession(Session session){
		this.sessionSet.add(session);
        for (Device device : deviceSet) {
            JsonObject addMessage = createAddMessage(device);
            sendToSession(session, addMessage);
        }
	}
	
	public void removeSession(Session session){
		this.sessionSet.remove(session);
	}
	
	public List<Device> getDevices(){
		return new ArrayList<Device>(deviceSet);
	}
	
	public void addDevice(Device device){
        device.setId(deviceId);
        deviceSet.add(device);
        deviceId++;
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(addMessage);		
	}
	
	public void removeDevice(int id){
        Device device = getDeviceById(id);
        if (device != null) {
            deviceSet.remove(device);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }		
	}
	
	public void toggleDevice(int id){
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            if ("On".equals(device.getStatus())) {
                device.setStatus("Off");
            } else {
                device.setStatus("On");
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggle")
                    .add("id", device.getId())
                    .add("status", device.getStatus())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }		
	}
	
	private Device getDeviceById(int id){
        for (Device device : deviceSet) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
	}
	
	private JsonObject createAddMessage(Device device){
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device.getId())
                .add("name", device.getName())
                .add("type", device.getType())
                .add("status", device.getStatus())
                .add("description", device.getDescription())
                .build();
        return addMessage;
	}
	
	private void sendToAllConnectedSessions(JsonObject message){
        for (Session session : sessionSet) {
            sendToSession(session, message);
        }
	}
	
	private void sendToSession(Session session, JsonObject message){
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessionSet.remove(session);
            Logger.getLogger(DeviceSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }		
	}
}

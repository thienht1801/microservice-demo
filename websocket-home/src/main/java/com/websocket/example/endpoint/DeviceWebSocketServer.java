package com.websocket.example.endpoint;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.websocket.example.model.Device;

@ApplicationScoped
@ServerEndpoint("/ws_server")
public class DeviceWebSocketServer {
	
	
	private DeviceSessionHandler handler = new DeviceSessionHandler();
	
	@OnOpen
	public void open(Session session){
		handler.addSession(session);
	}
	
	@OnClose
	public void close(Session session){
		handler.removeSession(session);
	}

	@OnError
	public void onError(Throwable error){
		Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
	}
	
	@OnMessage
	public void handleMessage(String message, Session session){

        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("add".equals(jsonMessage.getString("action"))) {
                Device device = new Device();
                device.setName(jsonMessage.getString("name"));
                device.setDescription(jsonMessage.getString("description"));
                device.setType(jsonMessage.getString("type"));
                device.setStatus("Off");
                handler.addDevice(device);
            }

            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                handler.removeDevice(id);
            }

            if ("toggle".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                handler.toggleDevice(id);
            }
        }		
	}
}

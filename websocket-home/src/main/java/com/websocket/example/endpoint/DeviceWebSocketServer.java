package com.websocket.example.endpoint;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ServerEndpoint("/ws_server")
public class DeviceWebSocketServer {
	@OnOpen
	public void open(Session session){
		
	}
	
	@OnClose
	public void close(Session session){
		
	}

	@OnError
	public void onError(Throwable error){
		
	}
	
	@OnMessage
	public void handleMessage(String message, Session session){
		
	}
}

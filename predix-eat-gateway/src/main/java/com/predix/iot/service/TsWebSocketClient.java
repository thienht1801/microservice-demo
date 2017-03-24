/*
 * Copyright (c) 2016 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.predix.iot.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.predix.iot.eat.gateway.configuration.TimeseriesConfig;
import com.predix.iot.eat.gateway.configuration.UaaConfig;
import com.predix.iot.eat.gateway.util.UaaUtils;


@Component
public class TsWebSocketClient {

	private static Logger log = LoggerFactory.getLogger(TsWebSocketClient.class);

	private WebSocketContainer container = ContainerProvider.getWebSocketContainer();

	@Autowired
	private UaaConfig uaaConfig;

	@Autowired
	private TimeseriesConfig timeseriesConfig;

	private Session session;

	/**
	 * Post json object to WebSocket
	 * 
	 * @param json
	 * @throws TimeSeriesException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public synchronized void postJson(String json){
		if (log.isInfoEnabled()) {
			log.info(System.currentTimeMillis() + " : Session " + session.getId() + " is sending json message");
		}
		try {
			ByteBuffer data = ByteBuffer.wrap(json.getBytes("UTF8"));
			this.session.getBasicRemote().sendBinary(data);
		} catch (IOException e) {
			log.error("Time Series connection exception", e);
		}
	}

	/**
	 * Initilizate WebSocket session before using
	 * 
	 * @param endpoint
	 * @param clientConfig
	 * @param url
	 * @throws Exception 
	 * @throws TimeSeriesException
	 * @throws DeploymentException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public Session initSession(){
		if (this.session != null && this.session.isOpen()) {
			if (log.isDebugEnabled()) {
				log.debug("Session already opened: " + this.session.getId());
			}
		} else {
			log.info("Create new WebSocket session");
			try {
				getToken();
				this.session = createSession("Bearer " + getToken());
				this.session.setMaxIdleTimeout(30000);
				session.addMessageHandler(new MessageHandler.Whole<String>() {
					@SuppressWarnings("synthetic-access")
					@Override
					public void onMessage(String response) {
						if (log.isInfoEnabled()) {
							log.info("On Message connecting to websocket ... " + response);
						}
					}
				});
			} catch (DeploymentException | IOException | URISyntaxException e) {
				this.session = null;
				log.error(e.getMessage());
			}
		}
		return this.session;
	}

	/**
	 * Close current session
	 * 
	 * @throws TimeSeriesException
	 * 
	 * @throws IOException
	 */
	public void closeSession() throws IOException {
		CloseReason closeReason = new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Session ends normally");
		this.session.close(closeReason);
	}

	/**
	 * Create websocket session base on token and environment configuration
	 * 
	 * @param token
	 * @return
	 * @throws DeploymentException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private Session createSession(String token) throws DeploymentException, IOException, URISyntaxException {
		Endpoint endpoint = new TsWebSocketEndpoint();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.add("Predix-Zone-Id", timeseriesConfig.getInstanceId());
		headers.add("Origin", timeseriesConfig.getOrigin());

		ClientEndpointConfigurator configurator = new ClientEndpointConfigurator(headers);

		return container.connectToServer(endpoint,
				ClientEndpointConfig.Builder.create().configurator(configurator).build(),
				new URI(timeseriesConfig.getIngestUri()));
	}

	/**
	 * Get client token for Timeseries service
	 * 
	 * @return
	 */
	private String getToken() {
		return UaaUtils.getAdminAccessToken(uaaConfig);
	}
}

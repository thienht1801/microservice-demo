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

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TsWebSocketEndpoint extends Endpoint {

	private static Logger log = LoggerFactory.getLogger(TsWebSocketEndpoint.class);
	
	Session curSession;

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		this.curSession = session;
	}

	/**
	 * Define the behavior of the client message handler when the session is closed
	 * 
	 * @param session: WebSocket session
	 * @param closeReason: Provide details of session closing
	 */
	@OnClose
	@Override
	public void onClose(Session session, CloseReason closeReason) {
		if (log.isDebugEnabled()) {
			log.debug("Client: Session " + session.getId() + " closed because of " + closeReason.getReasonPhrase()); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}

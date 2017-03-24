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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.websocket.ClientEndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class ClientEndpointConfigurator extends ClientEndpointConfig.Configurator {

	private static final Logger logger = LoggerFactory.getLogger(ClientEndpointConfigurator.class);
	
	private HttpHeaders tsHeaders;

	public ClientEndpointConfigurator(HttpHeaders headers) {
		this.tsHeaders = headers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.websocket.ClientEndpointConfig.Configurator#beforeRequest(java.util
	 * .Map)
	 */
	@Override
	public void beforeRequest(Map<String, List<String>> headers) {
		super.beforeRequest(headers);
		for (Entry<String, List<String>> entry : this.tsHeaders.entrySet()) {
			headers.put(entry.getKey(), entry.getValue());
		}
		logger.info(headers.toString());
	}
}

/*
 * Copyright (c) 2016 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */

package com.predix.iot.eat.gateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "uaa")
public class UaaConfig {

	private String clientId;
	private String clientSecret;
	private String uri;
	private String clientBase64Token;

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getUri() {
		return uri;
	}

	public String getClientBase64Token() {
		return clientBase64Token;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setClientBase64Token(String clientBase64Token) {
		this.clientBase64Token = clientBase64Token;
	}

}

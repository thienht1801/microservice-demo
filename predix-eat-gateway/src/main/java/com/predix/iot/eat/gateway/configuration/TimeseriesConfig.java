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
@ConfigurationProperties(prefix = "timeseries")
public class TimeseriesConfig {

	private String ingestUri;
	private String zoneHeaderName;
	private String instanceId;
	private String origin;
	private String queryUri;
	
	public String getIngestUri() {
		return ingestUri;
	}
	
	public void setIngestUri(String ingestUri) {
		this.ingestUri = ingestUri;
	}
	
	public String getZoneHeaderName() {
		return zoneHeaderName;
	}
	
	public void setZoneHeaderName(String zoneHeaderName) {
		this.zoneHeaderName = zoneHeaderName;
	}
	
	public String getInstanceId() {
		return instanceId;
	}
	
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getQueryUri() {
		return queryUri;
	}
	
	public void setQueryUri(String queryUri) {
		this.queryUri = queryUri;
	}
	
}

package com.predix.geo.simulator.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawData_Feature {

	private String type;
	private RawData_Properties properties;
	private RawData_Geometry geometry;
	
	public RawData_Feature() {
		super();
	}

	public RawData_Feature(String type, RawData_Properties properties, RawData_Geometry geometry) {
		super();
		this.type = type;
		this.properties = properties;
		this.geometry = geometry;
	}
	
	public String getType() {
	return type;
	}

	public void setType(String type) {
	this.type = type;
	}

	public RawData_Properties getProperties() {
	return properties;
	}

	public void setProperties(RawData_Properties properties) {
	this.properties = properties;
	}

	public RawData_Geometry getGeometry() {
	return geometry;
	}

	public void setGeometry(RawData_Geometry geometry) {
	this.geometry = geometry;
	}


}
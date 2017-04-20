package com.predix.geo.simulator.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawData_Geometry {

	private String type;
	private List<List<Double>> coordinates = null;
	
	public RawData_Geometry(String type, List<List<Double>> coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}

	public RawData_Geometry() {
		super();
	}

	public String getType() {
	return type;
	}
	
	public void setType(String type) {
	this.type = type;
	}

	public List<List<Double>> getCoordinates() {
	return coordinates;
	}

	public void setCoordinates(List<List<Double>> coordinates) {
	this.coordinates = coordinates;
	}


}
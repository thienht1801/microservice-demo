package com.predix.geo.simulator.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawData_FeatureCollection {

	private String type;
	private List<RawData_Feature> features = null;
	
	public RawData_FeatureCollection() {
		super();
	}

	public RawData_FeatureCollection(String type, List<RawData_Feature> features) {
		super();
		this.type = type;
		this.features = features;
	}

	public String getType() {
	return type;
	}
	
	public void setType(String type) {
	this.type = type;
	}
	
	public List<RawData_Feature> getFeatures() {
	return features;
	}
	
	public void setFeatures(List<RawData_Feature> features) {
	this.features = features;
	}

}
package com.predix.geo.simulator.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TruckData{
	
	private String collection;
	private String asset;
	private List<Double> location;
	private Long timestamp;
	private Boolean retain_history;
	private Long purge_history_before;
	
	public TruckData(){

	}

	public TruckData(String collection, String asset, List<Double> location, Long timestamp,
			Boolean retain_history, Long purge_history_before) {
		super();
		this.collection = collection;
		this.asset = asset;
		this.location = location;
		this.timestamp = timestamp;
		this.retain_history = retain_history;
		this.purge_history_before = purge_history_before;
	}



	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public List<Double> getLocation() {
		return location;
	}

	public void setLocation(List<Double> location) {
		this.location = location;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getRetain_history() {
		return retain_history;
	}

	public void setRetain_history(Boolean retain_history) {
		this.retain_history = retain_history;
	}

	public Long getPurge_history_before() {
		return purge_history_before;
	}

	public void setPurge_history_before(Long purge_history_before) {
		this.purge_history_before = purge_history_before;
	}
}

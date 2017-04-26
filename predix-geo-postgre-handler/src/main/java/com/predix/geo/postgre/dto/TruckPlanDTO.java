package com.predix.geo.postgre.dto;

import java.util.Date;

public class TruckPlanDTO {

	private Long truckPlanId;
	private Date planDate;
	private String startPlace;
	private String destination;
	private String truckName;
	
	public Long getTruckPlanId() {
		return truckPlanId;
	}
	public void setTruckPlanId(Long truckPlanId) {
		this.truckPlanId = truckPlanId;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTruckName() {
		return truckName;
	}
	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}
	
	
}

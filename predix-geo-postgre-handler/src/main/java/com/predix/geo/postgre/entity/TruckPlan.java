package com.predix.geo.postgre.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "truck_plan")
public class TruckPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "truck_plan_id")
	private Long truckPlanId;
	
	@Column(name = "plan_date")
	private Date planDate;
	
	@Column(name = "start_place")
	private String startPlace;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "truck_name")
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

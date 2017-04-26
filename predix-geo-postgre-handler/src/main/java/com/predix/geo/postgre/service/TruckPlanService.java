package com.predix.geo.postgre.service;

import java.util.List;

import com.predix.geo.postgre.dto.TruckPlanDTO;

public interface TruckPlanService {

	List<TruckPlanDTO> getTruckPlanByTruckName(String truckName);
}

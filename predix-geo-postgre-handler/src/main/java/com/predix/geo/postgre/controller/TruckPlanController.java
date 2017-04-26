package com.predix.geo.postgre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.predix.geo.postgre.dto.TruckPlanDTO;
import com.predix.geo.postgre.service.TruckPlanService;

@RestController
public class TruckPlanController {
	
	@Autowired
	private TruckPlanService truckPlanService;

	@RequestMapping(value = "/truck_plan", method = RequestMethod.GET)
	public ResponseEntity<List<TruckPlanDTO>> getCategoriesByName(@RequestParam("truckName") String truckName) {
		List<TruckPlanDTO> truckPlans = truckPlanService.getTruckPlanByTruckName(truckName);
		return new ResponseEntity<>(truckPlans, HttpStatus.OK);
	}
	
}
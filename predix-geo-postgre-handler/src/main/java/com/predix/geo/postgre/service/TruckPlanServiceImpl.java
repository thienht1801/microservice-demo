package com.predix.geo.postgre.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.predix.geo.postgre.dto.TruckPlanDTO;
import com.predix.geo.postgre.entity.TruckPlan;
import com.predix.geo.postgre.repo.TruckPlanRepository;

@Service
public class TruckPlanServiceImpl implements TruckPlanService{

	@Autowired
	TruckPlanRepository truckPlanRepo;
	
	@Override
	public List<TruckPlanDTO> getTruckPlanByTruckName(String truckName) {
		if(null != truckName && !"".equals(truckName)){
			return convertEntityToDTO(truckPlanRepo.getTruckPlanByName(truckName));
		}else{
			throw new IllegalArgumentException("Missing truck name.");
		}
	}

	private List<TruckPlanDTO> convertEntityToDTO(List<TruckPlan> entities){
		List<TruckPlanDTO> dtoList = new ArrayList<TruckPlanDTO>();		
		if(null != entities && !entities.isEmpty()){
			for(TruckPlan truckplan: entities){
				TruckPlanDTO dto = new TruckPlanDTO();
				dto.setTruckPlanId(truckplan.getTruckPlanId());
				dto.setTruckName(truckplan.getTruckName());
				dto.setStartPlace(truckplan.getStartPlace());
				dto.setDestination(truckplan.getDestination());
				dto.setPlanDate(truckplan.getPlanDate());
				dtoList.add(dto);
			}
		}		
		return dtoList;
	}
}

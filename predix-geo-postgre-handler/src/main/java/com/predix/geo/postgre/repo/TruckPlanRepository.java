package com.predix.geo.postgre.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.predix.geo.postgre.entity.TruckPlan;

@Repository
public interface TruckPlanRepository extends PagingAndSortingRepository<TruckPlan, Long>, JpaSpecificationExecutor<TruckPlan> {

	@Query("SELECT t FROM TruckPlan t WHERE lower(t.truckName) = lower(:truckName)")
	public List<TruckPlan> getTruckPlanByName(@Param("truckName") String truckName);

}
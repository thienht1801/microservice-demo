package com.predix.geo.simulator;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.predix.geo.simulator.configuraton.UaaConfiguration;
import com.predix.geo.simulator.entity.RawData_Feature;
import com.predix.geo.simulator.entity.RawData_FeatureCollection;
import com.predix.geo.simulator.entity.RawData_Geometry;
import com.predix.geo.simulator.entity.TruckData;
import com.predix.geo.simulator.exception.CustomException;
import com.predix.geo.simulator.service.GatewayClient;
import com.predix.geo.simulator.utils.UaaUtils;

@Component
public class TruckDataSimulator {

	private static final Logger logger = LoggerFactory.getLogger(TruckDataSimulator.class);

	@Autowired
	private GatewayClient client;

	@Autowired
	UaaConfiguration uaaConfig;

	private String token;
		
	@Scheduled(fixedDelayString = "${scheduler.time}")
	public void feedData() {
		logger.info("Start feeding truck data to gateway");
		generateData();
	}

	@Async
	protected void generateData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			RawData_FeatureCollection rawData = new RawData_FeatureCollection();
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("GeoSpike_Test.json");

			rawData = mapper.readValue(inputStream, RawData_FeatureCollection.class);
			//logger.info("RAW DATA: " + mapper.writeValueAsString(rawData));

			List<RawData_Feature> featureList = rawData.getFeatures();
			int timePeriod = 0;
			int purge_period = 7;
			if(null != featureList){
				for(RawData_Feature fearture: featureList){
					TruckData truckData = new TruckData();
					String truckName = getTruckName();
					logger.info("Generate Data for Truck: " + truckName);
					truckData.setAsset(truckName);
					truckData.setCollection("trucks");
					truckData.setRetain_history(true);
					
					RawData_Geometry geometry = fearture.getGeometry();
					List<List<Double>> coordinateLine = geometry.getCoordinates();
					
					for(List<Double> coordinate : coordinateLine){
						truckData.setLocation(coordinate);
						
						long now = System.currentTimeMillis();
						truckData.setTimestamp(now + (timePeriod * 1000));
						truckData.setPurge_history_before(now - (purge_period * 24 * 60 * 60 * 1000));
						String payload = mapper.writeValueAsString(truckData);
						sendData(payload);
						
						timePeriod = timePeriod + 3;
					}
					timePeriod = 0;
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error in generateData:" + e);
		}

	}

	private <T> void sendData(String truckData) {
		String payload = truckData;
		// send message to data gateway
		try {
			client.postTruckDataToGateway(payload);
		} catch (CustomException e) {
			e.printStackTrace();
			logger.error("Error in sendData:" + e);
		}
	}

	@PostConstruct
	private void putTokenToCache() {
		try {
			token = UaaUtils.getAdminAccessToken(uaaConfig);
			JSONObject jsonObject = UaaUtils.getAccountInfoInUaa(token, uaaConfig);
			Long expiredTime = (jsonObject.getLong("exp") * 1000);
			UaaUtils.putTokenToCache(token, expiredTime);
		} catch (Exception ex) {
			logger.warn("Can't get the token in uaa", ex);
		}
	}

	private String getTruckName(){
		Random r = new Random();
		int min = 1;
		int max = 25;
		int randomTruckNumber = r.nextInt(max-min) + min;
		
		return "GE_" + String.format("%09d", randomTruckNumber);
	}
}

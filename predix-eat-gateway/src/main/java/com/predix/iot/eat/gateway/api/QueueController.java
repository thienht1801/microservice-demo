package com.predix.iot.eat.gateway.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.predix.timeseries.model.builder.IngestionRequestBuilder;
import com.ge.predix.timeseries.model.builder.IngestionTag;
import com.ge.predix.timeseries.model.datapoints.DataPoint;
import com.ge.predix.timeseries.model.datapoints.Quality;
import com.predix.iot.eat.gateway.entity.BuildingEntity;
import com.predix.iot.eat.gateway.rabbitmq.QueuePublisher;
import com.predix.iot.service.TsWebSocketClient;


@RestController
public class QueueController {

	private static Logger log = Logger.getLogger(QueueController.class);

	@Autowired
	private QueuePublisher publisher;

	@Autowired
	private TsWebSocketClient tsClient;
	
	private ObjectMapper mapper = new ObjectMapper();
	/**
	 * Query API with create queue message
	 *
	 * @return ResponseEntity object
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping(value = "/queues", method = RequestMethod.POST)
	public ResponseEntity<Void> createQueue(@RequestBody String timeserieData) throws JsonParseException, JsonMappingException, IOException {
		log.info("Create queue  message for time series data: " + timeserieData);
		publisher.sendTimeseriesMessage(timeserieData);
		
		BuildingEntity buildingInfo = mapper.readValue(timeserieData, BuildingEntity.class);
		if(null == buildingInfo){
			log.info("ERROR IN PARSING BUILDING ENTITY");
		}
		
		String payloadContent = buildTsRequest(buildingInfo);
		if(null != payloadContent){
			// send data to timeseries
			log.info("INGESTING TIMESERIES........");
			tsClient.initSession();
			tsClient.postJson(payloadContent);
			log.info("INGEST TIMESERIES SUCESSFULLY");
		}else{
			log.info("ERROR INGESTING TIMESERIES");
		}
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	private String buildTsRequest(BuildingEntity timeserieData) {
		String messageId = "message" + System.currentTimeMillis();
		IngestionRequestBuilder ingestionBuilder = IngestionRequestBuilder.createIngestionRequest()
				.withMessageId(messageId);

		ingestionBuilder.addIngestionTag(IngestionTag.Builder.createIngestionTag().withTagName("indoor_temp")
				.addDataPoints(Arrays
						.asList(new DataPoint(new Date().getTime(), timeserieData.getInDoorTemp(), Quality.GOOD)))
				.addAttribute("buildingId", timeserieData.getId().toString()).build());

		ingestionBuilder.addIngestionTag(IngestionTag.Builder.createIngestionTag().withTagName("outdoor_temp")
				.addDataPoints(Arrays
						.asList(new DataPoint(new Date().getTime(), timeserieData.getOutDoorTemp(), Quality.GOOD)))
				.addAttribute("buildingId", timeserieData.getId().toString()).build());
		
		ingestionBuilder.addIngestionTag(IngestionTag.Builder.createIngestionTag().withTagName("ennergy_consumption")
				.addDataPoints(Arrays
						.asList(new DataPoint(new Date().getTime(), timeserieData.getEnergyConsume(), Quality.GOOD)))
				.addAttribute("buildingId", timeserieData.getId().toString()).build());

		try {
			return ingestionBuilder.build().get(0);
		} catch (IOException e) {
			log.error("IOException when build request", e);
		}

		return null;
	}
}

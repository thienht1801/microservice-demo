package com.predix.geo.simulator.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.predix.geo.simulator.configuraton.GatewayAPIConfiguration;
import com.predix.geo.simulator.configuraton.UaaConfiguration;
import com.predix.geo.simulator.exception.CustomException;
import com.predix.geo.simulator.utils.UaaUtils;

@Component
public class GatewayClientImpl implements GatewayClient {

	private static Logger log = LoggerFactory.getLogger(GatewayClientImpl.class);

	@Autowired
	private UaaConfiguration uaaConfig;

	@Autowired
	private GatewayAPIConfiguration gatewayAPIConfiguration;

	private String token;

	private Client client = ClientBuilder.newClient().property(ClientProperties.CONNECT_TIMEOUT, 10000);
	ObjectMapper mapper = new ObjectMapper();
	WebTarget target;


	/**
	 * check token in cache is expired or not
	 * */
	private void checkTokenInCache(){
		if(token != null) {
			if(UaaUtils.isTokenExpired(token)) {
				UaaUtils.removeTokenInCache(token);
				putTokenToCache();
				log.info("**********Sending data to gateway API TOKEN IS EXPIRED **********");
			}
		} else {
			putTokenToCache();
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
			log.warn("Can't get the token in uaa", ex);
		}
	}

	private void sendDataToGateway(String message, String token) throws CustomException {
		try {
			log.info("Send message: " + message);
			target = client.target(gatewayAPIConfiguration.getUri()).path("queues");
			Response response = target.request(MediaType.APPLICATION_JSON_VALUE)
					.header("Authorization", "Bearer " + token)
					.post(Entity.entity(message, MediaType.APPLICATION_JSON_VALUE));
			log.info("Raw Response : " + response);
		} catch (Exception ex) {
			log.error("Exception in sendDataToGateway: ", ex);
			throw new CustomException(ex);
		}
	}

	public void postTruckDataToGateway(String message) throws CustomException {
		// TODO Auto-generated method stub
		checkTokenInCache();
		sendDataToGateway(message,token);

	}

}

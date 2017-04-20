package com.predix.geo.simulator.service;

import com.predix.geo.simulator.exception.CustomException;

public interface GatewayClient {

	public void postTruckDataToGateway(String message) throws CustomException;

}

package com.predix.iot.eat.gateway.util;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

import org.glassfish.jersey.client.ClientProperties;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.predix.iot.eat.gateway.configuration.UaaConfig;


public class UaaUtils {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(UaaUtils.class);

	/** The Constant AUTHORIZATION. */
	private static final String AUTHORIZATION = "Authorization";

	/** The Constant CONNECTION_TIMEOUT. */
	private static final int CONNECTION_TIMEOUT = 3000;

	/** The Constant READ_TIMEOUT. */
	private static final int READ_TIMEOUT = 3000;

	/** The client. */
	private static Client client = null;

	static {
		client = ClientBuilder.newClient()
				.property(ClientProperties.CONNECT_TIMEOUT, CONNECTION_TIMEOUT)
				.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
	};

	// cache the token with the Expired time
	//this is just for memory cache for each module
	private static final Map<String, Long> USERINFO_LOCAL_CACHE = new HashMap<String, Long>();
	 /**
	 * Gets the admin access token.
	 *
	 * @param uaaConfig the uaa config
	 * @return return token of client id
	 */
	public static String getAdminAccessToken(UaaConfig uaaConfig) {
		String uaaUri = uaaConfig.getUri();
		String clientBase64Token = uaaConfig.getClientBase64Token();
		try {
			WebTarget target = client.target(uaaUri).path("oauth/token");
			Form form = new Form();
			form.param("grant_type", "client_credentials");
			String response = target
					.request()
					.header(AUTHORIZATION, "Basic " + clientBase64Token)
					.post(Entity.entity(form,
							MediaType.APPLICATION_FORM_URLENCODED_VALUE),
							String.class);
			JSONObject jsonObject = new JSONObject(response);
			return jsonObject.getString("access_token");
		} catch (Exception ex) {
			logger.error("Exception in getAdminAccessToken: ", ex);
			return null;
		}
	}

	/**
	 * put a token to local cache
	 * @param token token to save
	 * @param expiredTime allInfo to save
	 * */
	public static void putTokenToCache(String token, Long expiredTime) {
		USERINFO_LOCAL_CACHE.put(token, expiredTime);
	}

	public static JSONObject getAccountInfoInUaa(String token, UaaConfig uaaConfig) {
		try {
			WebTarget target = client.target(uaaConfig.getUri()).path("check_token");
			Form form = new Form();
			form.param("token", token);
			String response = target
					.request()
					.header(AUTHORIZATION, "Basic " + uaaConfig.getClientBase64Token())
					.post(Entity.entity(form,
							MediaType.APPLICATION_FORM_URLENCODED_VALUE),
							String.class);
			JSONObject jsonObject = new JSONObject(response);
			return jsonObject;
		} catch (Exception ex) {
			logger.error("Exception in getAllAccountInfo: ", ex);
			return null;
		}
	}
	/**
	 * check the token is expired or not
	 * @param token the token in cache
	 * */
	public static boolean isTokenExpired(String token) {
		Long expiredTime = USERINFO_LOCAL_CACHE.get(token);
		if(expiredTime == null || expiredTime.longValue() == 0) {
			return true;
		} 
		boolean isExpired = System.currentTimeMillis() > expiredTime.longValue();
		return isExpired;
	}
	
	/**
	 * remove token in local cache
	 * @param token token to save
	 * */
	public static void removeTokenInCache(String token) {
		USERINFO_LOCAL_CACHE.remove(token);
	}
}

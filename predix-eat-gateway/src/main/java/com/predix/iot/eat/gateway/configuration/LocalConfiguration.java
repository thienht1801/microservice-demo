package com.predix.iot.eat.gateway.configuration;

import java.net.URI;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="rabbitmq")
public class LocalConfiguration {

	private String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Bean
	public ConnectionFactory rabbitConnectionFactory() throws Exception {
		return new CachingConnectionFactory(new URI(uri));
	}
}

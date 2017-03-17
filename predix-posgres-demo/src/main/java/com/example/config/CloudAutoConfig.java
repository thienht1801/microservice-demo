/**
 * 
 */
package com.example.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("cloud")
@ServiceScan
public class CloudAutoConfig extends AbstractCloudConfig {

	@Bean
    @Primary
	public DataSource getDataSource() {
		return connectionFactory().dataSource();
	}

}

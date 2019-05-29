package com.dell.tsp.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
//@EnableDiscoveryClient
public class Application implements EnvironmentAware {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void setEnvironment(Environment environment) {
		LOG.info("Application running with active profiles: " + Arrays.toString(environment.getActiveProfiles()));
	}
}
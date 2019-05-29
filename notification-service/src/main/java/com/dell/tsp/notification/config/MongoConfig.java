package com.dell.tsp.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration{

	 @Value("${spring.data.mongodb.host}") 
	  private String HOST;
	  
	 @Value("${spring.data.mongodb.port}") 
	  private int PORT;
	 
	 @Value("${spring.data.mongodb.database}") 
	  private String DATABASE;
	 
	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return new MongoClient(HOST, PORT);
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return DATABASE;
	}

	
}

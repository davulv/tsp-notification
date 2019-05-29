package com.dell.tsp.notification.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dell.tsp.notification.repository.SampleRepository;

@Service
public class SampleService {
	
	private static Logger LOG = LoggerFactory.getLogger(SampleService.class);
	
	private SampleRepository sampleRepository;

	@Autowired
	public SampleService(SampleRepository sampleRepository) {
		this.sampleRepository = sampleRepository;
	}

	public String paymentOptions(String paymentoptions) {
		LOG.info("paymentoptions: Service : fetch value with {} " , paymentoptions);
		return sampleRepository.paymentOptions(paymentoptions);
	}
}

package com.dell.tsp.notification.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dell.tsp.notification.VO.*;
import com.dell.tsp.notification.service.NotificationService;

@Component
public class QueueConsumer {
	
	@Autowired
	 NotificationService notificationService;
	
	 protected Logger logger = LoggerFactory.getLogger(getClass());
	 
	 
	 private void processMessage(EmailPayloadTemplate emailPayloadTemplate) {
		  
		  logger.info("QueueConsumer: processMessage() " + emailPayloadTemplate);
		  try {
			notificationService.processPayload(emailPayloadTemplate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("message", e);
		}
		  
	 }

}

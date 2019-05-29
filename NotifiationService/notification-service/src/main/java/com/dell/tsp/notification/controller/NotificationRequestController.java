package com.dell.tsp.notification.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dell.tsp.notification.VO.EmailDocumentTemplate;
import com.dell.tsp.notification.VO.EmailPayloadTemplate;
import com.dell.tsp.notification.service.NotificationService;


import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class NotificationRequestController {

	 private static Logger LOG = LoggerFactory.getLogger(SampleController.class);
		
		private NotificationService notificationService;
		
		
		@Autowired
		public NotificationRequestController(NotificationService notificationService) {
			this.notificationService = notificationService;
		}

		
		@PostMapping(value = "/v1/notification")
		public ResponseEntity<String> processNotification(@RequestBody EmailPayloadTemplate payloadTemplate) throws IOException {
			LOG.info("processPayload: controller : fetch value with {} " , payloadTemplate);
			return ResponseEntity.ok().body(notificationService.processPayload(payloadTemplate));
		}
		
		
		@GetMapping(value = "/v1/notification/{subscriberId}")
		public ResponseEntity<List<EmailDocumentTemplate>> getNotificationsBySubscriberId(@PathVariable("subscriberId") int subscriberId) {
			LOG.info("getNotificationsBySubscriberId: controller : fetch value with {} " , subscriberId);
			return ResponseEntity.ok().body(notificationService.getNotificationsBySubscriberId(subscriberId));
		}
		
		
}

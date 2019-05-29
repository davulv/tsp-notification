package com.dell.tsp.notification.service;

import com.dell.tsp.notification.VO.EmailPayloadTemplate;

import java.io.IOException;
import java.util.List;

import com.dell.tsp.notification.VO.EmailDocumentTemplate;

public interface NotificationService {

	public String processPayload(EmailPayloadTemplate emailPayloadTemplate) throws IOException;

	public List<EmailDocumentTemplate> getNotificationsBySubscriberId(int subscriberId);
	
}

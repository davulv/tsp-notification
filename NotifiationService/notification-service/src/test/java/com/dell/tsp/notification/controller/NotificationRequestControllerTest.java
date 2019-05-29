package com.dell.tsp.notification.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.PathVariable;

import com.dell.tsp.notification.VO.EmailDocumentTemplate;
import com.dell.tsp.notification.VO.EmailPayloadTemplate;
import com.dell.tsp.notification.service.NotificationService;
import com.dell.tsp.notification.service.SampleService;
import java.io.IOException;
import static org.mockito.Mockito.*;

public class NotificationRequestControllerTest {

	@InjectMocks
	private NotificationRequestController notificationRequestController;

	@Mock
	private NotificationService notificationService;
	
	@Mock
	private EmailPayloadTemplate emailPayloadTemplate;
	
	@Mock
	private List<EmailDocumentTemplate> mockList;

	
	  @Before
	  public void setup() { 
		  MockitoAnnotations.initMocks(this);
	  notificationRequestController = new NotificationRequestController(notificationService); 
	  }
	  

	  @Test 
	  public void testProcessNotification() throws IOException{
	  when(notificationService.processPayload(emailPayloadTemplate)).thenReturn("sent successfully");
	  ResponseEntity<String> returnedResponse= notificationRequestController.processNotification(emailPayloadTemplate);
	  assertThat(returnedResponse.getBody(), equalTo("sent successfully"));
	  assertThat(returnedResponse.getStatusCode(), equalTo(HttpStatus.OK));
	  }
	  
	  @Test (expected = MailSendException.class)
	  public void testProcessNotification_Exception() throws IOException{
	  when(notificationService.processPayload(emailPayloadTemplate)).thenThrow(new MailSendException("Invalid Addresses")).thenReturn("failed");
	  ResponseEntity<String> returnedResponse= notificationRequestController.processNotification(emailPayloadTemplate);
	  assertThat(returnedResponse.getBody(), equalTo("failed"));
	  assertThat(returnedResponse.getStatusCode(), equalTo(HttpStatus.OK));
	  }
	  
	  @Test 
	  public void testGetNotificationsBySubscriberId(){
	  when( notificationService.getNotificationsBySubscriberId(anyInt())).thenReturn(mockList);
	  ResponseEntity<List<EmailDocumentTemplate>> returnedResponse= notificationRequestController.getNotificationsBySubscriberId(1);
	  assertThat(returnedResponse.getBody(), equalTo(mockList));
	  assertThat(returnedResponse.getStatusCode(), equalTo(HttpStatus.OK));
	  }
	 
}

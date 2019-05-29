package com.dell.tsp.notification.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.dell.tsp.notification.VO.EmailDocumentTemplate;
import com.dell.tsp.notification.VO.EmailPayloadTemplate;
import com.dell.tsp.notification.repository.EmailRepository;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Component
public class NotificationServiceImpl implements NotificationService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${SendGrid_API_KEY}")
	private String API_KEY;
	
	@Autowired
	private EmailRepository emailRepository;

	
	@Override
	public String processPayload(EmailPayloadTemplate emailPayloadTemplate) throws IOException {
		logger.info("NotificationServiceImpl: processPayload() for subscriber ID: " + emailPayloadTemplate.getSubscriberId() );
		Email from= new Email(emailPayloadTemplate.getFrom());
		Email to = new Email(emailPayloadTemplate.getTo());
		Content content = new Content("text/Plain", emailPayloadTemplate.getBody());
		String subject = emailPayloadTemplate.getSubject();
		Mail mail = new Mail(from,subject,to, content);
		
		EmailDocumentTemplate emailDocumentTemplate= new EmailDocumentTemplate();
		SendGrid sg = new SendGrid(API_KEY);
	    Request request = new Request();
		try {
			emailDocumentTemplate.setBcc(emailPayloadTemplate.getBcc());
			emailDocumentTemplate.setTo(emailPayloadTemplate.getTo());
			emailDocumentTemplate.setCc(emailPayloadTemplate.getCc());
			emailDocumentTemplate.setFrom(emailPayloadTemplate.getFrom());
			emailDocumentTemplate.setSubject(emailPayloadTemplate.getSubject());
			emailDocumentTemplate.setSubscriberId(emailPayloadTemplate.getSubscriberId());
			emailDocumentTemplate.setBody(emailPayloadTemplate.getBody());
			emailDocumentTemplate.setTimeStamp(emailPayloadTemplate.getTimeStamp());
			emailDocumentTemplate.setStatus("sent successfully");
			request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      logger.info("Status Code of mail: ", response.getStatusCode());
		     
		    } catch (IOException ex) {
		    	emailDocumentTemplate.setStatus("failed");
		      throw ex;
		    }catch (Exception e) {
			emailDocumentTemplate.setStatus("failed");
			logger.error(e.getMessage());
		} finally {
			logger.info("inside finally");
			emailRepository.save(emailDocumentTemplate);
		}
		return emailDocumentTemplate.getStatus();
	}

	@Override
	public List<EmailDocumentTemplate> getNotificationsBySubscriberId(int subscriberId) {
		logger.info("NotificationServiceImpl: getNotificationsBySubscriberId() " + subscriberId);
		return emailRepository.findBySubscriberId(subscriberId);
	}

}

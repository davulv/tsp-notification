package com.dell.tsp.notification.VO;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EmailPayloadTemplate implements Serializable{

	private long subscriberId;
	private String to;
	private String from;
	private String cc;
	private String bcc;
	private String body;
	private String subject;
	private Date timeStamp;
	
	public EmailPayloadTemplate( 
			@JsonProperty("to") String to, @JsonProperty("from") String from,
			@JsonProperty("cc")String cc, @JsonProperty("bcc") String bcc,
			@JsonProperty("subscriberId") long subscriberId,
			@JsonProperty("body") String body,
			@JsonProperty("subject") String subject,
			@JsonProperty("timeStamp") Date timeStamp) {
		super();
		this.subscriberId = subscriberId;
		this.to = to;
		this.from = from;
		this.cc = cc;
		this.bcc = bcc;
		this.body = body;
		this.subject = subject;
		this.timeStamp = timeStamp;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public long getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(long subscriberId) {
		this.subscriberId = subscriberId;
	}
	@Override
	public String toString() {
		return "EmailPayloadTemplate [subscriberId=" + subscriberId + ", to=" + to + ", from=" + from
				+ ", cc=" + cc + ", bcc=" + bcc + ", body=" + body + ", subject=" + subject + ", timeStamp=" + timeStamp
				+ "]";
	}
	
}

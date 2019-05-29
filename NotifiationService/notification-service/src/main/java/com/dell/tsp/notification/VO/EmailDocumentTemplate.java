package com.dell.tsp.notification.VO;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class EmailDocumentTemplate {

	@Id
	private String id;
	private long subscriberId;
	private String to;
	private String from;
	private String cc;
	private String bcc;
	private String body;
	private String subject;
	private Date timeStamp;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(long subscriberId) {
		this.subscriberId = subscriberId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "EmailDocumentTemplate [id=" + id + ", subscriberId=" + subscriberId + ", to=" + to + ", from=" + from
				+ ", cc=" + cc + ", bcc=" + bcc + ", body=" + body + ", subject=" + subject + ", timeStamp=" + timeStamp
				+ ", status=" + status + "]";
	}
	
	
}

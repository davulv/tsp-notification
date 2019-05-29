package com.dell.tsp.notification.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dell.tsp.notification.VO.EmailDocumentTemplate;

public interface EmailRepository extends MongoRepository<EmailDocumentTemplate, String>{

	
	List<EmailDocumentTemplate> findBySubscriberId(int subscriberId);

	
}

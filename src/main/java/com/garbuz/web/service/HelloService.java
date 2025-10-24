package com.garbuz.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.garbuz.web.model.HelloMessage;

@Service
public class HelloService {
	
	private static final Logger LOG = LoggerFactory.getLogger(HelloService.class);

	public HelloMessage lookUpByFirstAndLastName(final String firstName, final String lastName) {
		
		LOG.info("Lokiking up message by {} and {}", firstName, lastName);
		
		HelloMessage msg = new HelloMessage();
		msg.setFirstName(firstName);
		msg.setLastName(lastName);
		msg.setMessage("Message from Message Service");
		
		
		LOG.info("Found {} message ", msg);
		return msg;
	}
	
}

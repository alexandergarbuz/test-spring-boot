package com.garbuz.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garbuz.web.model.Message;
import com.garbuz.web.repository.MessageDao;

@Service
public class HelloService {
	
	private static final Logger LOG = LoggerFactory.getLogger(HelloService.class);
	
	@Autowired
	private MessageDao messageDao;

	public Message lookUpByFirstAndLastName(final String firstName, final String lastName) {
		LOG.info("Lokiking up message by {} and {}", firstName, lastName);
		Message msg = this.messageDao.findByLastNameAndFirstName(lastName, firstName).stream().findFirst().orElse(new Message());
		LOG.info("Found {} message ", msg);
		return msg;
	}
	
	public List<Message> findAllMessages() {
		LOG.info("Looking up all messages");
		final List<Message> messages = this.messageDao.findAll();
		LOG.info("Found {} messages in total", messages.size());
		return this.messageDao.findAll();
	}
	
}

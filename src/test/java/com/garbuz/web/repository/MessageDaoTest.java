package com.garbuz.web.repository;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MessageDaoTest {
	@Autowired
	private MessageDao messageDao;
	
    @Test
    void messageTableShouldBePreloaded() {
        var messages = messageDao.findAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(messages));
    }
}

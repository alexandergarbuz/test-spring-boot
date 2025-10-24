package com.garbuz.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garbuz.web.model.Message;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MessageDao  extends JpaRepository<Message,Long> {
	
	List<Message> findByLastNameAndFirstName(final String lastName, final String firstName);

}

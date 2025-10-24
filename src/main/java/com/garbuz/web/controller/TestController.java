package com.garbuz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.garbuz.web.dto.HelloDto;
import com.garbuz.web.model.HelloMessage;
import com.garbuz.web.service.HelloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/hello-world")
@Tag(name = "SpringBoot Test Project", description = "Demo controller")
public class TestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private HelloService helloService;
	
	@GetMapping("/hello")
	@Operation(summary="Displays simple Hello World message")
	public String helloWorldEndpoint() {
		return "Hello World";
	}
	
	@GetMapping("/hello-with-name")
	@Operation(summary="Displays personalized hello message")
	public String sayHelloEndpoint(
			@RequestParam("firstName")
			final String firstName, 
			@RequestParam("lastName")
			final String lastName) {
		
		return "Hello " + firstName + " " + lastName;
	}
	
	@GetMapping("/hello-with-object")
	@Operation(summary="Displays personalized hello message as JSON object")
	public ResponseEntity<HelloDto> displayHelloClass(
			@RequestParam("firstName")
			final String firstName, 
			@RequestParam("lastName")
			final String lastName) {
		
		LOG.info("Saying Hello to {} {}", firstName, lastName );
		
		final HelloMessage msg = this.helloService.lookUpByFirstAndLastName(firstName, lastName);

		final HelloDto response = new HelloDto();
		response.setFirstName(msg.getFirstName());
		response.setLastName(msg.getLastName());
		response.setMessage("Saying Hello with " + msg.getMessage());
		
		LOG.info("Received message {}", msg);
		
		return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
	}
}

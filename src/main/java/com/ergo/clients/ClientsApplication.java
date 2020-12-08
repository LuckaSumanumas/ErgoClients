package com.ergo.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientsApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(ClientsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ClientsApplication.class, args);
		
		LOGGER.info("Starting application");
	}

}

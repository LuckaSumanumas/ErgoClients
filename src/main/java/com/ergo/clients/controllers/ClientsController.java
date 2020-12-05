package com.ergo.clients.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ergo.clients.Response;
import com.ergo.clients.ResponseBuilder;
import com.ergo.clients.entities.Client;

import services.ClientsService;

/**
 * 
 * @author Mindaugas Lucka
 * 
 * Controller for retrieving, creating and updating clients
 *
 */
@RestController
@RequestMapping("/api")
public class ClientsController {

	@Autowired
	private ClientsService clientsService;
	
	@GetMapping("/clients")
	public ResponseEntity<Response<?>> retrieveClients() {
		try {
			List<Client> clients = clientsService.retrieveClients();
			return new ResponseBuilder<Client>("success", "clients", clients).build();
		} catch (Exception e) {
			return new ResponseBuilder<Client>("error", "clients", HttpStatus.NOT_FOUND, e.getMessage()).build();
		}
	}
	
}

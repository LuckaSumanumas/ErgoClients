package com.ergo.clients.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ergo.clients.Response;
import com.ergo.clients.ResponseBuilder;
import com.ergo.clients.entities.Client;
import com.ergo.clients.services.ClientService;

/**
 * 
 * @author Mindaugas Lucka
 * 
 * Controller for retrieving, creating and updating clients
 *
 */
@RestController
@RequestMapping("/api")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/clients")
	public ResponseEntity<Response<?>> retrieveClients(
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate) {
		try {
			List<Client> clients = clientService.retrieveClients(startDate, endDate);
			return new ResponseBuilder<Client>("success", "clients", clients).build();
		} catch (Exception e) {
			return new ResponseBuilder<Client>("error", "clients", HttpStatus.NOT_FOUND, e.getMessage()).build();
		}
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<?> retrieveClientById(@PathVariable String id) {
		try {
			Client client = clientService.findClientById(id);
			return new ResponseBuilder<Client>("success", "clients", client).build();
		} catch (Exception e) {
			return new ResponseBuilder<Client>("error", "clients", HttpStatus.BAD_REQUEST, e.getMessage()).build();
		}
	}
	
	@PostMapping("/clients")
	public ResponseEntity<Response<?>> addClient(@RequestBody Client client) {
		try {
			Client newClient = clientService.addOrUpdateClient(client);
			return new ResponseBuilder<Client>("success", "clients", newClient).build();
		} catch (Exception e) {
			return new ResponseBuilder<Client>("error", "clients", HttpStatus.BAD_REQUEST, e.getMessage()).build();
		}
	}
	
}

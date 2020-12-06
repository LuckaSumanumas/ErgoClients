package com.ergo.clients.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ergo.clients.entities.Client;

/**
 * 
 * @author Mindaugas Lucka
 *
 */
@Service
public class ClientService {

	public List<Client> retrieveClients() {
		List<Client> clients = new ArrayList<Client>();
		return clients;
	}

}

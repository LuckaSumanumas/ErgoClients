package com.ergo.clients.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ergo.clients.ClientHelper;
import com.ergo.clients.entities.Client;
import com.ergo.clients.repositories.ClientRepository;

/**
 * 
 * @author Mindaugas Lucka
 *
 */
@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> retrieveClients(
			String startDateStr, String endDateStr) throws Exception {
		
		List<Client> clients = new ArrayList<Client>();
		
		LocalDate startDate = ClientHelper.retrieveLocalDate(startDateStr);
		LocalDate endDate = ClientHelper.retrieveLocalDate(endDateStr);
		
		if(startDate == null && endDate == null) {
			clients = retrieveAllClients();
		} else {
			clients = retrieveClientsByBirthDates(startDate, endDate);
		}
		
		return clients;
	}

	public Client addOrUpdateClient(Client client) throws Exception {
	
		Client savedClient = null;
		ClientHelper.validateGender(client.getGender());
		
		savedClient = clientRepository.save(client);
		return savedClient;

	}

	public Client findClientById(String id) throws Exception {
		
		Integer clientId = ClientHelper.parseClientId(id);
		if(clientId == null) {
			throw new Exception("It was provided wrong client id");
		}

		Optional<Client> existClient = 
				clientRepository.findById(clientId);
		
		if(!existClient.isPresent()) {
			throw new Exception("There wasn't found any client by client id " + clientId);
		}
		
		return existClient.get();
			
	}
	
	private List<Client> retrieveAllClients() throws Exception {
		List<Client> clients = new ArrayList<Client>();
		Iterable<Client> it = clientRepository.findAll();
		it.forEach(c -> clients.add(c));
		
		if(clients == null || clients.isEmpty()) {
			throw new Exception("There are no client records in the database");
		}
		
		return clients;
		
	}
	
	private List<Client> retrieveClientsByBirthDates(
			LocalDate startDate, LocalDate endDate) throws Exception {
		List<Client> clients = new ArrayList<Client>();
		
		ClientHelper.validateDates(startDate, endDate);
			
		clients = clientRepository.findByBirthDateAfterAndBirthDateBefore(
				startDate.minusDays(1), endDate.plusDays(1));
		
		if(clients == null || clients.isEmpty()) {
			throw new Exception("There are no client records by provided birth dates duration");
		}
		
		return clients;
	}
	
}

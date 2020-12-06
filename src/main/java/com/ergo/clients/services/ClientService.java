package com.ergo.clients.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ergo.clients.entities.Client;
import com.ergo.clients.entities.Gender;
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
		
		LocalDate startDate = retrieveLocalDate(startDateStr);
		LocalDate endDate = retrieveLocalDate(endDateStr);
		
		if(startDate == null && endDate == null) {
			clients = retrieveAllClients();
		} else {
			clients = retrieveClientsByBirthDates(startDate, endDate);
		}
		
		return clients;
	}

	public Client addOrUpdateClient(Client client) throws Exception {
	
		Client savedClient = null;
	
		validateGender(client.getGender());
		
		savedClient = clientRepository.save(client);
		return savedClient;

	}

	public Client findClientById(String id) throws Exception {
		
		Integer clientId = parseClientId(id);
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
	
	private Integer parseClientId(String id) {
		Integer clientId = null;
		try {
			clientId = Integer.valueOf(id);
		} catch (NumberFormatException e) {
			
		}
		
		return clientId;
	}
	
	private LocalDate retrieveLocalDate(String startDateStr) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			return startDateStr != null ? LocalDate.parse(startDateStr, formatter) : null;
		} catch(DateTimeParseException  e) {
			throw new Exception("It was provided wrong date " + startDateStr + "; " + e.getMessage());
		}
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
		
		if((startDate == null && endDate != null) 
				|| (startDate != null && endDate == null)) {
			throw new Exception("It should be provided either no dates or both dates");
		}
		
		clients = clientRepository.findByBirthDateAfterAndBirthDateBefore(startDate, endDate);
		
		if(clients == null || clients.isEmpty()) {
			throw new Exception("There are no client records by provided birth dates duration");
		}
		
		return clients;
	}
	
	private void validateGender(String gender) throws Exception {
		if(!gender.toUpperCase().equals(Gender.MALE.toString()) && 
				!gender.toUpperCase().equals(Gender.FEMALE.toString())) {
			throw new Exception("Gender should be either of these values: " + 
				Arrays.toString(Gender.values()));
		}
		
	}
}

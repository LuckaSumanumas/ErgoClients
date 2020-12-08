package com.ergo.clients;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ergo.clients.entities.Client;
import com.ergo.clients.repositories.ClientRepository;
import com.ergo.clients.services.ClientService;

@RunWith(SpringRunner.class)
public class ClientsApplicationTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public ClientService contractService() {
			return new ClientService();
		}
	}

	@Autowired
	private ClientService clientService;

	@MockBean
	private ClientRepository clientRepository;

	@Before
	public void setUp() {
    	
	}
	
	@Test
	public void testRetrieveClients() throws Exception {
		List<Client> clients = buildListOfClients();

		Mockito.when(clientRepository.findAll()).thenReturn(clients);

		List<Client> response = clientService.retrieveClients(null, null);

		assertTrue(response.size() > 0);
		assertTrue(response.get(0).getFirstName().equals("Name 1"));
	}

	private List<Client> buildListOfClients() throws Exception {
		List<Client> clients = new ArrayList<Client>();
		clients.add(buildClient1());

		return clients;
	}

	private Client buildClient1() throws Exception {
		Client client = new Client();
		client.setPersonaId(1);
		client.setFirstName("Name 1");
		client.setLastName("Surname 1");
		client.setBirthDate(ClientHelper.retrieveLocalDate("15-08-1991"));
		client.setGender("male");

		return client;
	}

}

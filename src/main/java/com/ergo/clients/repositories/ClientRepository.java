package com.ergo.clients.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ergo.clients.entities.Client;

/**
 * 
 * @author Mindaugas Lucka
 *
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

	List<Client> findByBirthDateAfterAndBirthDateBefore(
			@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
}

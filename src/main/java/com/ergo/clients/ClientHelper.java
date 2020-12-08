package com.ergo.clients;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import com.ergo.clients.entities.Gender;

public class ClientHelper {

	public static LocalDate retrieveLocalDate(String startDateStr) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			return startDateStr != null ? LocalDate.parse(startDateStr, formatter) : null;
		} catch(DateTimeParseException  e) {
			throw new Exception("It was provided wrong date " + startDateStr + "; " + e.getMessage());
		}
	}
	
	public static Integer parseClientId(String id) {
		Integer clientId = null;
		try {
			clientId = Integer.valueOf(id);
		} catch (NumberFormatException e) {
			
		}
		
		return clientId;
	}
	
	public static void validateDates(LocalDate startDate, LocalDate endDate) throws Exception {
		if((startDate == null && endDate != null) 
				|| (startDate != null && endDate == null)) {
			throw new Exception("It should be provided either no dates or both dates");
		} else if(startDate.isAfter(endDate)) {
			throw new Exception("'To date' could not be latter than 'From date'");
		}
	}
	
	public static void validateGender(String gender) throws Exception {
		if(!gender.toUpperCase().equals(Gender.MALE.toString()) && 
				!gender.toUpperCase().equals(Gender.FEMALE.toString())) {
			throw new Exception("Gender should be either of these values: " + 
				Arrays.toString(Gender.values()));
		}
		
	}
}

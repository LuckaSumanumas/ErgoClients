package com.ergo.clients.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Mindaugas Lucka
 *
 */
@Entity @Table(name = "clients", 
uniqueConstraints=@UniqueConstraint(columnNames={"first_name", "last_name", "birth_date"}))
public class Client {
	@Id @GeneratedValue @Column(name = "client_id")
	private Integer personaId;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "birth_date", nullable = false)
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate birthDate;
	@Column(name = "gender", nullable = false)
	private String gender;
	
	public Integer getPersonaId() {
		return personaId;
	}
	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}

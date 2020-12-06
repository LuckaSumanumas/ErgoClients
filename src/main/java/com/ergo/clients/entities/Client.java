package com.ergo.clients.entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Mindaugas Lucka
 *
 */
@Entity @Table(name = "clients")
@Getter @Setter @NoArgsConstructor
public class Client {
	@Id @GeneratedValue @Column(name = "client_id")
	private Integer personaId;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "birth_date", nullable = false)
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dateOfBirth;
	@Column(name = "gender", nullable = false, length = 1)
	private String gender;
	
}

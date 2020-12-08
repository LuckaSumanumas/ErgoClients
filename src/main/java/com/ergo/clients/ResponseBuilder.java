package com.ergo.clients;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @author Mindaugas Lucka
 * 
 * Restful APIs response builder
 * @param <T>
 *
 */
public class ResponseBuilder<T> {
	private final String status;
	private final String type;
	private final HttpStatus httpStatus;
	private final T entity;
	private final List<T> entities;
	private final String message;
	
	public ResponseBuilder(String status, String type, T entity) {
		this.status = status;
		this.httpStatus = HttpStatus.OK;
		this.type = type;
		this.entity = entity;
		this.entities = null;
		this.message = null;
	}
	
	public ResponseBuilder(String status, String type, List<T> entities) {
		this.status = status;
		this.httpStatus = HttpStatus.OK;
		this.type = type;
		this.entity = null;
		this.entities = entities;
		this.message = null;
	}
	
	public ResponseBuilder(String status, String type, HttpStatus httpStatus, String message) {
		this.status = status;
		this.httpStatus = httpStatus;
		this.type = type;
		this.entity = null;
		this.entities = null;
		this.message = message;
	}
	
	public ResponseEntity<Response<?>> build() {
		Response<T> response;
		if(this.entity != null) {
			response = new Response<T>(
					this.status, this.type, this.message, this.entity);
		} else if(this.entities != null) {
			response = new Response<T>(
					this.status, this.type, this.message, this.entities);
		} else {
			response = new Response<T>(
					this.status, this.type, this.message);
		}
		
        return ResponseEntity.status(httpStatus).body(response);
    }
}
package com.ergo.clients;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Mindaugas Lucka
 * 
 * Restful APIs response
 *
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
	@JsonProperty(required = true)
	private String status;
	@JsonProperty(required = true)
	private String type;
	@JsonProperty(required = false)
	private T entity;
	@JsonProperty(required = false)
	private List<T> entities;
	@JsonProperty(required = false)
	private String message;
	
	public Response(String status, String type, String message) {
		this.status = status;
		this.type = type;
		this.message = message;
	}
	
	public Response(String status, String type, String message, T entity) {
		this.status = status;
		this.type = type;
		this.message = message;
		this.entity = entity;
	}
	
	public Response(String status, String type, String message, List<T> entities) {
		this.status = status;
		this.type = type;
		this.message = message;
		this.entities = entities;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
	
}
package com.nelioalves.workshopmongo.dto;

import java.io.Serializable;

import com.nelioalves.workshopmongo.domain.User;

public class AutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;

	//Construtores
	public AutorDTO() {
	}
	
	public AutorDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	//Getters e Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

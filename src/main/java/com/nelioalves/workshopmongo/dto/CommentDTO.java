package com.nelioalves.workshopmongo.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CommentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String text;
	private LocalDate date;
    private String author;

	//Construtores
	public CommentDTO() {
	}

	public CommentDTO(String text, LocalDate date, String author) {
		super();
		this.text = text;
		this.date = date;
		this.author = author;
	}
	
	//Getters e Setters
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
}
package com.nelioalves.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	/*Lembrete: Esse método instancia o UserRepository
	 * O UserRepository herda o MongoRepository
	 * O método findAll usado é do MongoRepository*/
	public List<User> findAll(){
		return repo.findAll();
	}
}

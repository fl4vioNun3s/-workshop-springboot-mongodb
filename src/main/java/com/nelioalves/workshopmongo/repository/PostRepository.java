package com.nelioalves.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nelioalves.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post,String>{

	//Query-Method
	List<Post> findByTitleContainingIgnoreCase(String text);
}

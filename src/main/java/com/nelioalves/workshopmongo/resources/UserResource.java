package com.nelioalves.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;

@RestController
@RequestMapping(value= "/users")
public class UserResource {

	/* Lembrete: Eu tô instanciando o UserService
	 * O UserService instancia o UserRepository
	 * Ou seja, o Controlador conversa com o Serviço, que conversa com o Repositório.
	 */
	
	@Autowired
	private UserService service;
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = service.findById(id);
		UserDTO userDTO = new UserDTO(user);
		return ResponseEntity.ok().body(userDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
		User user = service.fromDTO(userDTO);
		service.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/ìd}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id){
		User user = service.fromDTO(userDTO);
		user.setId(id); //Para garantir que o objeto tenha o Id da requisição.
		service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}

}

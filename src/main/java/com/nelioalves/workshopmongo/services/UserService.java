package com.nelioalves.workshopmongo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrando!"));
	}

	/*
	 * Lembrete: Esse método instancia o UserRepository O UserRepository herda o
	 * MongoRepository O método findAll usado é do MongoRepository
	 */
	public List<User> findAll() {
		return repo.findAll();
	}

	public User insert(User user) {
		return repo.insert(user);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	/*
	 * Vai buscar um objeto no banco de dados, e atualizar com os novos dados que
	 * vieram pela requisição. Antes dos dados serem atualizados, eles não tem
	 * vinculo nenhum com o código ou o banco de dados.
	 */
	public User update(User obj) {
		try {
			Optional<User> newUser = repo.findById(obj.getId()); //Pego o id no corpo porque a ideia é atualizar um dado já existente.
			User user = newUser.get();
			updateData(user, obj);
			return repo.save(user);
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException("Object not found.");
		}
	}
	
	private void updateData(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}

}

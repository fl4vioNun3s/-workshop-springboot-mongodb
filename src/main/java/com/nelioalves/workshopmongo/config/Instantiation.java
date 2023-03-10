package com.nelioalves.workshopmongo.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.AutorDTO;
import com.nelioalves.workshopmongo.dto.CommentDTO;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.repository.UserRepository;

// Para o Spring entender que é uma configuração
@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		//Para limpar a coleção no MongoDB
		userRepository.deleteAll();
		postRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//Salva os novos dados no MongoDB automaticamente
		userRepository.saveAll(Arrays.asList(maria,alex,bob));

		
		Post post1 = new Post(null, LocalDate.parse("21/03/2018", fmt)
				, "Partiu Viagem!", "Vou viajar para São Paulo. Abraços!", new AutorDTO(maria));
		
		Post post2 = new Post(null, LocalDate.parse("23/03/2018", fmt)
				,"Bom dia!", "Acordei feliz hoje.", new AutorDTO(maria));
		
		//Comentarios dos posts
		CommentDTO comment1 = new CommentDTO("Boa viagem, mano!", LocalDate.parse("21/03/2018", fmt), "Alex Green");
		CommentDTO comment2 = new CommentDTO("Aproveite!", LocalDate.parse("22/03/2018", fmt), "Bob Grey");
		CommentDTO comment3 = new CommentDTO("Tenha um otimo dia!", LocalDate.parse("23/03/2018", fmt), "Alex Green");
		
		post1.getComments().addAll(Arrays.asList(comment1,comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		
		//Salva os novos dados no MongoDB automaticamente
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		//Associa os posts com a Maria e salva eles.
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);

	}

}

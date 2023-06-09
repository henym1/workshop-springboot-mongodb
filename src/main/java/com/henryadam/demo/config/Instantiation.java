package com.henryadam.demo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.henryadam.demo.domain.Post;
import com.henryadam.demo.domain.User;
import com.henryadam.demo.dto.AuthorDTO;
import com.henryadam.demo.dto.CommentDTO;
import com.henryadam.demo.repository.PostRepository;
import com.henryadam.demo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("05/06/2023"), "Que teclado ruim", "Nunca mais compro coisa da Redragon", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("09/06/2023"), "Sextou", "Cucko hoje?", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Uma merda mesmo", sdf.parse("05/06/2023"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Hoje é Nuvem", sdf.parse("09/06/2023"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Cagaram na pistinha", sdf.parse("09/06/2023"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1));
		post2.getComments().addAll(Arrays.asList(c2, c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}


	
}

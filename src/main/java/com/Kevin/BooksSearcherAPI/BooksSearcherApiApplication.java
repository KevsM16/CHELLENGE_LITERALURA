package com.Kevin.BooksSearcherAPI;

import com.Kevin.BooksSearcherAPI.principal.Principal;
import com.Kevin.BooksSearcherAPI.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksSearcherApiApplication implements CommandLineRunner{

	@Autowired
	LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BooksSearcherApiApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal= new Principal(repository);
		principal.correMenu();
	}
}

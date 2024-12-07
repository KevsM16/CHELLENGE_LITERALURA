package com.Kevin.BooksSearcherAPI;

import com.Kevin.BooksSearcherAPI.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksSearcherApiApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(BooksSearcherApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal= new Principal();
		principal.correMenu();
	}
}

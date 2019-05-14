package de.internship.server;

import de.internship.server.model.Book;
import de.internship.server.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	@Autowired
	private BookRepository repository;

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ServerApplication.class, args);
	}

}

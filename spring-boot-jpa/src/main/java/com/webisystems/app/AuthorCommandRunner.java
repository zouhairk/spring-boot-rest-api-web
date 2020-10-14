package com.webisystems.app;

import java.time.LocalDate;

 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.webisystems.app.DAO.AuthorRepository;
import com.webisystems.app.bo.Author;

@Component
public class AuthorCommandRunner implements CommandLineRunner {

	@Autowired
	AuthorRepository authorService;

	  final static  Logger log =
			  LoggerFactory.getLogger(AuthorCommandRunner.class);
	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 10; i++) {
			Author a = new Author("Zouhair KASMI", LocalDate.of(1991,i+1,28));
			authorService.save(a);
			log.info("User added :"+ a);
		}
	}

}

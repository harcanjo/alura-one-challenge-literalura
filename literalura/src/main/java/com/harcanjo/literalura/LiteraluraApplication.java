package com.harcanjo.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.harcanjo.literalura.main.MainMenu;
import com.harcanjo.literalura.repository.AutorRepository;
import com.harcanjo.literalura.repository.BookRepository;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainMenu mainMenu = new MainMenu(authorRepository, bookRepository);
		mainMenu.showMenu();
	}

}

package com.harcanjo.literalura.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.harcanjo.literalura.model.Author;
import com.harcanjo.literalura.model.Book;
import com.harcanjo.literalura.model.BooksSearchData;
import com.harcanjo.literalura.repository.AutorRepository;
import com.harcanjo.literalura.service.APIConsumption;
import com.harcanjo.literalura.service.ConvertData;

public class MainMenu {

	private Scanner scanner = new Scanner(System.in);
	private final String URL_BASE = "http://gutendex.com/books/?search=";
	private AutorRepository repository;

	public MainMenu(AutorRepository repository) {
		this.repository = repository;
	}

	public void showMenu() {

		String optionsMenu = """
				
					---------------------------------------------
					Escolha o número de sua opção:
					1- buscar livro pelo título
					2- listar livros registrados
					3- listar autores registrados
					4- listar autores vivos em um determinado ano
					5- listar livros em um determinado idioma
					0- sair					
				 	""";

		var userOption = "";

		while(!userOption.contains("0")) {
			System.out.println(optionsMenu);

			userOption = scanner.nextLine();

			switch (userOption) {
				case "1": {
					searchBookByTitle();
					break;
				}
				case "2": {
					listRegisteredBooks();
					break;
				}
				case "3": {
					listRegisteredAuthors();
					break;
				}
				case "4": {
					listLivingAuthorsInAGivenYear();
					break;
				}
				case "5": {
					listBooksInAParticularLanguage();
					break;
				}
				case "0": {
					System.out.println("Até a próxima!");
					break;
				}
			default:
				System.out.println("Opção inválida");;
			}
		}

	}

	private void listBooksInAParticularLanguage() {
		// TODO Auto-generated method stub

	}

	private void listLivingAuthorsInAGivenYear() {
		// TODO Auto-generated method stub
		System.out.println("Escolha um ano: ");
		int userYear = scanner.nextInt();
		List<Author> authorsAlive = repository.findAll();
		
		boolean foundAuthors = false;

		for (Author author : authorsAlive) {
		    if (author.getBirthYear() <= userYear && author.getDeathYear() >= userYear) {
		        System.out.println("Autor encontrado: " + author.getName());
		        foundAuthors = true;
		    }
		}

		if (!foundAuthors) {
		    System.out.println("Nenhum autor encontrado para o ano especificado.");
		}		
	}

	private void listRegisteredAuthors() {
		List<Author> authors = repository.findAll();
		authors.forEach(System.out::println);
	}

	private void listRegisteredBooks() {
		// TODO: 
		// List<Book> books = repository.findB;
		
	}

	private void searchBookByTitle() {
		System.out.println("\nInsira o nome do livro que você deseja procurar:\n");
		var bookTitle = scanner.nextLine();
		var apiConsumption = new APIConsumption();
		var json = apiConsumption.getData(URL_BASE + bookTitle.replace(" ", "%20").toLowerCase());
		// System.out.println(json);
		
		ConvertData conversor = new ConvertData();
		BooksSearchData data = conversor.getData(json, BooksSearchData.class);		
		
		// System.out.println(data.results().stream().findFirst().get().author().get(0).name());
		
		if(!data.results().isEmpty()) {			
				
			// TODO: Checar se autor já está cadastrado					
			Author author = new Author(data);
			repository.save(author);
			
			Optional<Author> authorBook = repository.findByNameContainingIgnoreCase(author.getName());			 

			Book book = new Book(data);
			
			if (authorBook.isPresent()) {
				book.setAuthor(authorBook.get());
				authorBook.get().getBooks().add(book);
				repository.save(authorBook.get());
			}
			
			System.out.println("----- LIVRO -----");
			System.out.println("Título: " + book.getTitle());
			System.out.println("Autor: " + author.getName());
			System.out.println("Idioma: " + book.getLanguage());
			System.out.println("Número de downloads: " + book.getDownloadsCount());
			System.out.println("-----------------");
			
		} else {
			System.out.println("Livro não encontrado...");
		}
		
	}


}

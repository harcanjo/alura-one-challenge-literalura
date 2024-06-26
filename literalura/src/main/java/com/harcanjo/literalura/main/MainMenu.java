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
	private final String URL = "http://gutendex.com/books/?search=";
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

	}

	private void listRegisteredAuthors() {
		List<Author> authors = repository.findAll();
		authors.forEach(System.out::println);
	}

	private void listRegisteredBooks() {
		List<Book> books = repository.findAll();
		books.forEach(System.out::println);
	}

	private void searchBookByTitle() {
		System.out.println("\nDigite o título do livro:\n");
		var bookTitle = scanner.nextLine();
		var apiConsumption = new APIConsumption();
		var json = apiConsumption.getData(URL + bookTitle.replace(" ", "%20").toLowerCase());

		ConvertData conversor = new ConvertData();
		BooksSearchData data = conversor.getData(json, BooksSearchData.class);

		if(!data.results().isEmpty()) {
				
			// TODO: Checar se autor já está cadastrado		
			
			var bookResults = data.results().get(0);
			
			var authorResults = bookResults.author().get(0);
			var name = authorResults.name();
			var birthYear = Integer.parseInt(authorResults.birthYear());
			var deathYear = Integer.parseInt(authorResults.deathYear());
			Author author = new Author(name, birthYear, deathYear);
			repository.save(author);
			
			var title = bookResults.title();
			Optional<Author> authorBook = repository.findByNameContainingIgnoreCase(name);
			String language = bookResults.language().stream().findFirst().orElse(null);
			var downloadsCount = Long.parseLong(bookResults.downloadCount());
			 
			if (authorBook.isPresent()) {
				Book book = new Book(title, language, downloadsCount);
				book.setAuthor(authorBook.get());
				authorBook.get().getBooks().add(book);
				repository.save(authorBook.get());
			}
		} else {
			System.out.println("Livro não encontrado...");
		}
	}


}

package com.harcanjo.literalura.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.harcanjo.literalura.model.Author;
import com.harcanjo.literalura.model.Book;
import com.harcanjo.literalura.model.BooksSearchData;
import com.harcanjo.literalura.repository.AutorRepository;
import com.harcanjo.literalura.repository.BookRepository;
import com.harcanjo.literalura.service.APIConsumption;
import com.harcanjo.literalura.service.ConvertData;

public class MainMenu {

	private Scanner scanner = new Scanner(System.in);
	private final String URL_BASE = "http://gutendex.com/books/?search=";
	private AutorRepository authorRepository;
	private BookRepository bookRepository;

	public MainMenu(AutorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
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
		// TODO Implementar categorias e permitir busca pelo
		System.out.println("""
				Insira o idioma para realizar a busca:
				en - inglês
				es - espanhol
				fr - francês
				it - italiano
				pt - português
				""");
		String languageToSearch = scanner.nextLine();
		List<Book> booksByLanguage = bookRepository.findByLanguageContainingIgnoreCase(languageToSearch);
		if (!booksByLanguage.isEmpty()) {
		    // System.out.println("Livros encontrados para o idioma " + languageToSearch + ":");
		    booksByLanguage.forEach(book -> System.out.println(book));
		} else {
		    System.out.println("Não existem livros nesse idioma no banco de dados.");
		}
	}

	private void listLivingAuthorsInAGivenYear() {
		// TODO Alterar a exibição
		System.out.println("Insira o ano que deseja pesquisar ");
		int userYear = scanner.nextInt();
		scanner.nextLine();
		List<Author> authorsAlive = authorRepository.findAll();
		
		boolean foundAuthors = false;

		for (Author author : authorsAlive) {
		    if (author.getBirthYear() <= userYear && author.getDeathYear() >= userYear) {
		        System.out.println(author);
		        foundAuthors = true;
		    }
		}

		if (!foundAuthors) {
		    System.out.println("Nenhum autor encontrado vivo para o ano especificado.");
		}		
	}

	private void listRegisteredAuthors() {
		// TODO Alterar a exibição
		List<Author> authors = authorRepository.findAll();
		authors.forEach(System.out::println);
	}

	private void listRegisteredBooks() {
		// TODO Alterar a exibição
		List<Book> books = bookRepository.findAll();
		books.forEach(System.out::println);
	}

	private void searchBookByTitle() {
	    System.out.println("\nInsira o nome do livro que você deseja procurar:\n");
	    var bookTitle = scanner.nextLine();                
	    
	    var apiConsumption = new APIConsumption();
	    var json = apiConsumption.getData(URL_BASE + bookTitle.replace(" ", "%20").toLowerCase());
	        
	    ConvertData conversor = new ConvertData();
	    BooksSearchData data = conversor.getData(json, BooksSearchData.class);
	    
	    if (!data.results().isEmpty()) {            
	        Book book = new Book(data);
	        
	        // Verificar se o autor já existe no banco de dados
	        Optional<Author> existingAuthor = authorRepository.findByNameIgnoreCase(data.results().get(0).author().get(0).name());
	        
	        if (existingAuthor.isPresent()) {                
	            if (bookRepository.findByTitleIgnoreCase(data.results().get(0).title()).isEmpty()) {
	                book.setAuthor(existingAuthor.get());
	                existingAuthor.get().getBooks().add(book);
	                bookRepository.save(book);
	            } else {
	                 // System.out.println("O livro já existe no banco de dados.");
	            }
	        } else {
	            // System.out.println("Registrando o autor");
	            Author newAuthor = new Author(data);
	            newAuthor.getBooks().add(book);
	            book.setAuthor(newAuthor);
	            authorRepository.save(newAuthor);
	            bookRepository.save(book);
	        }
	        
	        book = bookRepository.findByTitleIgnoreCase(data.results().get(0).title())
	        	    .orElseGet(() -> {
	        	        // Lógica para criar um livro padrão (caso desejado)
	        	        return new Book();
	        	    });

	        System.out.println(book);
	        //System.out.println(bookRepository.findByTitleIgnoreCase(data.results().get(0).title()));
	    } else {
	        System.out.println("Livro não encontrado...");
	    }           
	}
	
	// TODO: Duplicity problems
	/*
	private void searchBookByTitle() {
		System.out.println("\nInsira o nome do livro que você deseja procurar:\n");
		var bookTitle = scanner.nextLine();				
		
		var apiConsumption = new APIConsumption();
		var json = apiConsumption.getData(URL_BASE + bookTitle.replace(" ", "%20").toLowerCase());
			
		ConvertData conversor = new ConvertData();
		BooksSearchData data = conversor.getData(json, BooksSearchData.class);
		
		if(!data.results().isEmpty()) {			
					
			Author author = new Author(data);
			authorRepository.save(author);
				
			Optional<Author> authorBook = authorRepository.findByNameIgnoreCase(author.getName());			 

			Book book = new Book(data);
				
			if (authorBook.isPresent()) {
				book.setAuthor(authorBook.get());
				authorBook.get().getBooks().add(book);
				authorRepository.save(authorBook.get());
			}
				
			System.out.println(book);
				
		} else {
			System.out.println("Livro não encontrado...");
		}			
	}
	*/
}

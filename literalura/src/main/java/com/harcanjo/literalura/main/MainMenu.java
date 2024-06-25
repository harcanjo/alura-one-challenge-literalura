package com.harcanjo.literalura.main;

import java.util.Scanner;

import com.harcanjo.literalura.model.DadosBuscaLivro;
import com.harcanjo.literalura.service.ConsumoApi;
import com.harcanjo.literalura.service.ConverteDados;

public class MainMenu {
	
	private Scanner scanner = new Scanner(System.in);
	private final String URL = "http://gutendex.com/books/?search=";
	
	public void showMenu() {
		
		String menu = """
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
			System.out.println(menu);
			
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
		// TODO Auto-generated method stub
		
	}

	private void listRegisteredBooks() {
		// TODO Auto-generated method stub
		
	}

	private void searchBookByTitle() {
		System.out.println("\nDigite o título do livro:\n");
		var bookTitle = scanner.nextLine();
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados(URL + bookTitle.replace(" ", "%20").toLowerCase());
		
		
		ConverteDados conversor = new ConverteDados();
		DadosBuscaLivro dados = conversor.obterDados(json, DadosBuscaLivro.class);
		
		if(!dados.resultados().isEmpty()) {
			System.out.println(dados.resultados().get(0).idioma()[0]);
		}
	}
	

}

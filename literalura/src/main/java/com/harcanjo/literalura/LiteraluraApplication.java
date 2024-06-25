package com.harcanjo.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.harcanjo.literalura.model.DadosLivro;
import com.harcanjo.literalura.service.ConsumoApi;
import com.harcanjo.literalura.service.ConverteDados;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		var consumoApi = new ConsumoApi();
		// http://gutendex.com/books/?search=dom%20casmurro
		// /books?search=dickens%20great
		var json = consumoApi.obterDados("http://gutendex.com/books/?search=dom%20casmurro");
		
		System.out.println(json);
		
		ConverteDados conversor = new ConverteDados();
		DadosLivro dados = conversor.obterDados(json, DadosLivro.class);
		System.out.println(dados);
		
	}

}

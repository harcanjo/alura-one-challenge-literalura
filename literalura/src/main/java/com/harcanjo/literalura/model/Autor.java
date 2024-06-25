package com.harcanjo.literalura.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private int nascimentoAno;
	
	private int morteAno;
	
	@OneToMany(mappedBy = "autor")
	private List<Livro> livros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNascimentoAno() {
		return nascimentoAno;
	}

	public void setNascimentoAno(int nascimentoAno) {
		this.nascimentoAno = nascimentoAno;
	}

	public int getMorteAno() {
		return morteAno;
	}

	public void setMorteAno(int morteAno) {
		this.morteAno = morteAno;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	@Override
	public String toString() {
		return "Autor nome=" + nome + ", nascimentoAno=" + nascimentoAno + ", morteAno=" + morteAno;
	}
	
	
}

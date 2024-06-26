package com.harcanjo.literalura.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String titulo;
	
	@ManyToOne
	private Autor autor;
	
	private String idioma;
	
	private Long numeroDeDownloads;
	
	public Livro() {}

	public Livro(String titulo, String idioma, Long numeroDeDownloads) {
		this.titulo = titulo;
		this.idioma = idioma;
		this.numeroDeDownloads = numeroDeDownloads;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Long getNumeroDeDownloads() {
		return numeroDeDownloads;
	}

	public void setNumeroDeDownloads(Long numeroDeDownloads) {
		this.numeroDeDownloads = numeroDeDownloads;
	}

	@Override
	public String toString() {
		return "Livro titulo=" + titulo + ", autor=" + autor.getNome() + ", idioma=" + idioma
				+ ", numeroDeDownloads=" + numeroDeDownloads;
	}

	
}

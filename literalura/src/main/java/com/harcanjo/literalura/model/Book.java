package com.harcanjo.literalura.model;

import java.util.OptionalLong;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String title;

	@ManyToOne
	private Author author;

	private String language;

	private Long downloadsCount;

	public Book() {}

	public Book(BooksSearchData bookSearch) {		
		var bookResults = bookSearch.results().get(0);		
		this.title = bookResults.title();
		this.language = bookResults.language().stream().findFirst().orElse(null);
		this.downloadsCount = OptionalLong.of(Long.valueOf(bookResults.downloadCount())).orElse(0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getDownloadsCount() {
		return downloadsCount;
	}

	public void setDownloadsCount(Long downloadsCount) {
		this.downloadsCount = downloadsCount;
	}

	@Override
	public String toString() {
		return "Livro titulo=" + title + 
				", autor=" + author.getName() + 
				", idioma=" + language + 
				", numero de downloads=" + downloadsCount;
	}


}

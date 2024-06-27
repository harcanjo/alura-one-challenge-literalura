package com.harcanjo.literalura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	private int birthYear;

	private int deathYear;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Book> books = new ArrayList<>();

	public Author() {}

	// TODO: This constructor needs to be refactored
	/* public Author(BooksSearchData bookSearch) {
		var bookResults = bookSearch.results().get(0);
		var authorResults = bookResults.author().get(0);
		this.name = authorResults.name();
		this.birthYear = OptionalInt.of(Integer.valueOf(authorResults.birthYear())).orElse(0);
		this.deathYear = OptionalInt.of(Integer.valueOf(authorResults.deathYear())).orElse(0);
	}*/
	
	public Author(BooksSearchData bookSearch) {
        var bookResults = bookSearch.results().get(0);
        var authorResults = bookResults.author().get(0);
        this.name = authorResults.name();
        this.birthYear = Optional.ofNullable(authorResults.birthYear())
                                 .filter(s -> !s.isEmpty())
                                 .map(Integer::valueOf)
                                 .orElse(0); 
        this.deathYear = Optional.ofNullable(authorResults.deathYear())
                                 .filter(s -> !s.isEmpty())
                                 .map(Integer::valueOf)
                                 .orElse(0);
        // TODO: test if this needs to be initialized here
        //this.books = bookResults.books() != null ? bookResults.books() : new ArrayList<>();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(int deathYear) {
		this.deathYear = deathYear;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		books.forEach(e -> e.setAuthor(this));
		this.books = books;
	}

	@Override
	public String toString() {
		return "\nAutor: " + name + "\n" + 
				"Ano de nascimento: " + birthYear + "\n" + 
				"Ano de falecimento: " + deathYear + "\n" +
				"Livros: [" + books.stream()
                					.map(Book::getTitle)
                					.collect(Collectors.joining(", ")) + "]";
	}


}

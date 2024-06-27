package com.harcanjo.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harcanjo.literalura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByLanguageContainingIgnoreCase(String languageToSearch);

	Book findByTitleContainingIgnoreCase(String bookTitle);

	boolean existsByTitleIgnoreCase(String title);

	Optional<Book> findByTitleIgnoreCase(String title);
}

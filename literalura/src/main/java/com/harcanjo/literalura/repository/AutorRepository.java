package com.harcanjo.literalura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harcanjo.literalura.model.Author;

public interface AutorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByNameContainingIgnoreCase(String name);
}

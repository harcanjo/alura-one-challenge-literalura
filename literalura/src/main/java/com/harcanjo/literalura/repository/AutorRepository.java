package com.harcanjo.literalura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harcanjo.literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

	Optional<Autor> findByNomeContainingIgnoreCase(String nome);
}

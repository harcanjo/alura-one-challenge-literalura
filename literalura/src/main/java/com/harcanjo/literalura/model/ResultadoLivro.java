package com.harcanjo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoLivro(
		@JsonProperty("title") String titulo) {
	
	/* (@JsonProperty("id") Long id,
		@JsonProperty("title") String titulo,
		@JsonProperty("authors") List<Autor> autores) */

}

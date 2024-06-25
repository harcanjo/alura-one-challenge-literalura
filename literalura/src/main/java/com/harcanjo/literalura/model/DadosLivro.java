package com.harcanjo.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
		@JsonProperty("title") String titulo,
		@JsonProperty("author") String autor,
		@JsonProperty("") String idioma,
		@JsonProperty("") Integer numeroDeDownloads) {
}

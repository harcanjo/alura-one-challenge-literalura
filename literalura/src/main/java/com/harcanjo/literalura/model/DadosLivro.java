package com.harcanjo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
		@JsonProperty("title") String titulo,
		@JsonProperty("authors") List<DadosAutor> autor,
		@JsonProperty("languages") String[] idioma,
		@JsonProperty("download_count") String numeroDeDownloads) {
}
